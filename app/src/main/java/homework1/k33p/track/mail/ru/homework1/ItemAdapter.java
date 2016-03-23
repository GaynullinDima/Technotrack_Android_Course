package homework1.k33p.track.mail.ru.homework1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(R.id.element_text);
        if ((position & 1) == 1) {
            convertView.setBackgroundResource(R.color.oddColor);
        } else {
            convertView.setBackgroundResource(R.color.evenColor);
        }
        text.setText(item.getText());

        return convertView;
    }
}