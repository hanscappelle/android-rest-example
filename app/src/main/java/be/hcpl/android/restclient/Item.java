package be.hcpl.android.restclient;

/**
 * Only an example for a model object, we'll need something to show
 *
 * Created by hanscappelle on 7/10/14.
 */
public class Item {

    private String title = null, description = null;

    private boolean checked = false;

    public Item(){

    }

    public Item(String title, String description, boolean checked) {
        this();
        this.title = title;
        this.description = description;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
