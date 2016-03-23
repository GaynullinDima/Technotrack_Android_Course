package homework1.k33p.track.mail.ru.homework1;

public class Item {

    private String text;

    public Item(String text) {
        this.text = text;
    }

    public Item(int i) {
        text = setTextAsNumberName(i);
    }

    public String getText() {
        return text;
    }

    public String setText(String text) {
        return this.text = text;
    }

    public String setTextAsNumberName(int i) {
        return Integer.toString(i);
    }
}
