package Client.Controller;

import Client.View.DialogWindow;

/**
 * @author
 * @version
 */
public class ChatUIEntity {
    private DialogWindow dialogwindow;
    private String name;

    public ChatUIEntity() {
        super();
    }

    public ChatUIEntity(DialogWindow dialogwindow, String name) {
        super();
        this.dialogwindow = dialogwindow;
        this.name = name;
    }

    public DialogWindow getDialogWindow() {
        return dialogwindow;
    }

    public void setDialogWindow(DialogWindow dialogwindow) {
        this.dialogwindow = dialogwindow;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

