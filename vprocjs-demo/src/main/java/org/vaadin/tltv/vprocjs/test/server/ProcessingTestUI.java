package org.vaadin.tltv.vprocjs.test.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.Action;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.data.util.FilesystemContainer;
import com.vaadin.v7.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.v7.ui.Tree;

/**
 * Test bench UI for ProcessingJs.
 *
 * @author Tltv
 *
 */
@Title("ProcessingJs Add-on Demo")
public class ProcessingTestUI extends UI {

    private static final long serialVersionUID = -2006622339916372647L;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ProcessingTestUI.class, widgetset = "org.vaadin.tltv.vprocjs.gwt.AppWidgetset")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.setMargin(false);
        setContent(layout);

        HorizontalLayout captionLayout = new HorizontalLayout();
        captionLayout.setSpacing(true);
        captionLayout.setMargin(new MarginInfo(true, false, false, true));

        Label caption = new Label("<span style='font-size:24px;'>ProcessingJs</span>", ContentMode.HTML);
        captionLayout.addComponent(caption);

        Label shortDescription = new Label(
                "<span style='color:gray;font-size:9px;vertical-align:bottom;'>Vaadin 8 component wrapping the <a href='http://processingjs.org' target='_blank'>processing.js</a> javascript library</span>",
                ContentMode.HTML);
        captionLayout.addComponent(shortDescription);

        layout.addComponent(captionLayout);

        TabSheet tabsheet = new TabSheet();
        tabsheet.addTab(new ProcessingCodeTest(), "Run with Processing code");
        tabsheet.addTab(new ProcessingSimpleJavaCodeTest(), "Run with Java code");
        tabsheet.addTab(new ProcessingSwitchJavaCodeTest(), "Switch Java code on fly");
        layout.addComponentsAndExpand(tabsheet);

        Panel panel = new Panel(new CssLayout(createSourceBrowser()));
        panel.setSizeFull();
        panel.getContent().setHeightUndefined();
        layout.addComponent(panel);
        layout.setExpandRatio(panel, 0.2f);
    }

    private Component createSourceBrowser() {
        String path = getSourcePath();
        FilesystemContainer c = new FilesystemContainer(new File(path), true);

        c.setFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String s) {
                boolean isfile = s.endsWith(".java") || s.endsWith(".xml") || s.endsWith(".js");
                if (isfile) {
                    return true;
                }
                File f = new File(dir.getAbsolutePath() + File.separator + s);
                if (f != null && f.isDirectory()) {
                    return true;
                }
                return false;
            }
        });

        Tree tree = new Tree("Source codes");
        tree.setWidth(100, Unit.PERCENTAGE);
        tree.setHeightUndefined();
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
                            Window w = new Window(f.getName(), new VerticalLayout());
                            w.setWidth("50%");
                            w.setHeight("90%");
                            ((VerticalLayout) w.getContent()).addComponent(l);
                            ((VerticalLayout) w.getContent()).setMargin(new MarginInfo(false, true, true, true));

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

    /**
     * Returns path to the Java source folder. By default, returns absolute path
     * pointing to webapp/WEB-INF/src. Absolute path is detected dynamically and
     * it may be null when web container runs the application within a war
     * package.
     *
     * @return Absolute path to the Java source files.
     */
    public String getSourcePath() {
        String path = getSession().getService().getBaseDirectory().getAbsolutePath() + File.separator + "WEB-INF"
                + File.separator + "classes";
        return path;
    }
}
