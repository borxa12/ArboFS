package com.santirodriguezlorenzo.arbofs.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.santirodriguezlorenzo.arbofs.R;
import com.santirodriguezlorenzo.arbofs.model.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Santi on 09/09/2015.
 */
public class TemplateAdapter extends BaseAdapter {

    private List<Player> players;
    private Activity activity;
    private int layout;
    private static LayoutInflater inflater=null;
    private View view;

    public TemplateAdapter(Activity activity, List<Player> players) {
        super();
        this.players = players;
        this.activity = activity;
        this.layout = R.layout.list_players;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public TemplateAdapter(Activity activity, List<Player> players, int layout) {
        super();
        this.players = players;
        this.activity = activity;
        this.layout = layout;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return players.size();
    }

    public Player getItem(int position) {
        return players.get(position);
    }

    public long getItemId(int position) {
        Player player = players.get(position);
        return player.get_id();
        //return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        view = convertView;
        Context context = parent.getContext();
        if(convertView == null){
            view = inflater.inflate(layout, null);
        }

        LinearLayout item = (LinearLayout) view.findViewById(R.id.item_player);
        if ((position % 2) == 0) {
            //item.setBackgroundColor(view.getResources().getColor(R.color.grey));
            item.setBackgroundResource(R.drawable.selector_grey);
        }else{
            //item.setBackgroundColor(view.getResources().getColor(R.color.grey_light));
            item.setBackgroundResource(R.drawable.selector_grey_light);
        }

        Player player = players.get(position);
        String name = player.getName();
        String pos = player.getPosition();

        TextView txtName = (TextView) view.findViewById(R.id.txt_name);
        txtName.setText(name);

        TextView txtPosition = (TextView) view.findViewById(R.id.txt_position);
        txtPosition.setText(pos);


        CircleImageView imgPlayer = (CircleImageView) view.findViewById(R.id.img_player);

        // load image
        try {
            // get input stream
            InputStream ims = activity.getAssets().open(player.getIdName() + ".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imgPlayer.setImageDrawable(d);
        }
        catch(IOException ex) {

        }
        /*
        CircleImageView imageView = (CircleImageView) view.findViewById(R.id.imgEvento);
        boolean cultural = evento.isCultural();
        if(cultural){
            if(evento.getImagenPortada() != null) {
                Picasso.with(context).load(evento.getImagenPortada().getLarge()).into(imageView);
            }else{
                imageView.setImageResource(R.drawable.img_agenda_cultural);
            }
        }else{
            if(evento.getImagenPortada() != null) {
                Picasso.with(context).load(evento.getImagenPortada().getLarge()).into(imageView);
            }else{
                imageView.setImageResource(R.drawable.img_agenda);
            }
        }*/


        return view;
    }

}
