package Client.Controller;

import Client.View.DialogWindow;



public class ChatUIEntity {
    private DialogWindow dialogwindow;
    private String id;

    public ChatUIEntity() {
        super();
    }

    public ChatUIEntity(DialogWindow dialogwindow, String id) {
        super();
        this.dialogwindow = dialogwindow;
        this.id = id;
    }

    public DialogWindow getDialogWindow() {
        return dialogwindow;
    }

    public void setDialogWindow(DialogWindow dialogwindow) {
        this.dialogwindow = dialogwindow;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}

