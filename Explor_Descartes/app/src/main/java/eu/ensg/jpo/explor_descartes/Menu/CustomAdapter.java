package eu.ensg.jpo.explor_descartes.Menu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import eu.ensg.jpo.explor_descartes.R;


public class CustomAdapter extends BaseAdapter {
    String result[];
    Context con;
    int img[];
    private static LayoutInflater inflater=null;
    public CustomAdapter(String[] result, Context con, int[] img) {
        this.result = result;
        this.con = con;
        this.img = img;
        inflater= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final holder hld=new holder();
        View rowview;
        rowview=inflater.inflate(R.layout.gdmain,null);

        //Association des variables avec le layout
        hld.tv= (TextView) rowview.findViewById(R.id.txt1);
        hld.iv= (ImageView) rowview.findViewById(R.id.img1);

        //Récupération des variables textes
        hld.tv.setText(result[position]);
        hld.iv.setImageResource(img[position]);
        hld.tv.setTextColor(Color.BLUE);
        return rowview;
    }
    public class holder{
        TextView tv;
        ImageView iv;
    }
}