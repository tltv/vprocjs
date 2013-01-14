package org.vaadin.tltv.vprocjs.test.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.event.Action;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProcessingTestUI extends UI {

    private static final long serialVersionUID = -2006622339916372647L;

    @Override
    protected void init(VaadinRequest request) {
        setContent(new VerticalLayout());

        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(new ProcessingCodeTest(), "Run with Processing code");
        tabsheet.addTab(new ProcessingSimpleJavaCodeTest(),
                "Run with Java code");
        tabsheet.addTab(new ProcessingSwitchJavaCodeTest(),
                "Switch Java code on fly");
        ((VerticalLayout) getContent()).addComponent(tabsheet);
        ((VerticalLayout) getContent()).addComponent(createSourceBrowser());
    }

    private Component createSourceBrowser() {
        String path = getSourcePath();
        FilesystemContainer c = new FilesystemContainer(new File(path), true);

        c.setFilter(new FilenameFilter() {
            @Override
            public boolean accept(File f, String s) {
                return true;
            }
        });

        Tree tree = new Tree("Source codes");
        tree.setContainerDataSource(c);
        tree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        tree.setItemCaptionPropertyId(FilesystemContainer.PROPERTY_NAME);

        final Action expandAll = new Action("Expand");
        tree.addActionHandler(new Action.Handler() {

            private static final long serialVersionUID = 1L;

            @Override
            public Action[] getActions(Object target, Object sender) {
                return new Action[] { expandAll };
            }

            @Override
            public void handleAction(Action action, Object sender, Object target) {
                if (sender instanceof Tree && action.equals(expandAll)) {
                    ((Tree) sender).expandItemsRecursively(target);
                }
            }
        });
        tree.addValueChangeListener(new ValueChangeListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object i = event.getProperty().getValue();
                if (i instanceof File) {
                    File f = (File) i;
                    if (!f.isDirectory()) {
                        try {
                            // Read the file
                            String java = readFile(f);

                            // Create a new Label for the Java code
                            Label l = new Label(java, ContentMode.PREFORMATTED);
                            // Create a new sub window and add label in it
                            Window w = new Window(f.getName(),
                                    new VerticalLayout());
                            w.setWidth("50%");
                            w.setHeight("90%");
                            ((VerticalLayout) w.getContent()).addComponent(l);
                            ((VerticalLayout) w.getContent())
                                    .setMargin(new MarginInfo(false, true,
                                            true, true));

                            if (w.getParent() != null) {
                                // window is already showing
                                Notification.show("Window is already open");
                            } else {
                                // Open the subwindow by adding it to the parent
                                // window
                                getUI().addWindow(w);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
        tree.setImmediate(true);
        return tree;
    }

    static public String readFile(File f) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader(f));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static ProcessingTestUI getTestRoot() {
        UI ui = getCurrent();
        if (ui instanceof ProcessingTestUI) {
            return (ProcessingTestUI) ui;
        }
        return null;
    }

    private String getSourcePath() {
        String path = getSession().getService().getBaseDirectory()
                .getAbsolutePath()
                + File.separator + "WEB-INF" + File.separator + "src";
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("vprocjs");
            if (bundle != null) {
                path = bundle.getString("src.tree.path");
            }
        } catch (MissingResourceException e) {
            // nop
        }
        return path;
    }
}
