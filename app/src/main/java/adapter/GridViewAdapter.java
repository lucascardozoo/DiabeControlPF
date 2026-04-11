package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.zip.Inflater;

import Entidad.Historial;
import frgp.utn.edu.ar.appmobile.R;

public class GridViewAdapter extends ArrayAdapter {
    private final List<Historial> items;

    public GridViewAdapter(Context context, List<Historial> items) {
        super(context, 0, items);
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Historial getItem(int position) {
        return items.get(position);
    }
    public long getItemId(int position){
        return position;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.gridview_item_template, null);
        }
        TextView txtFecha = (TextView) view.findViewById(R.id.txtFecha);
        TextView txtHora = (TextView) view.findViewById(R.id.txtHora);
        TextView txtTipo = (TextView) view.findViewById(R.id.txtEstAlimenticia);
        TextView txtGlucemia = (TextView) view.findViewById(R.id.txtGlucemia);
        TextView txtCarbo = (TextView) view.findViewById(R.id.txtCarbo);
        TextView txtInsulina = (TextView) view.findViewById(R.id.txtInsulina);
        TextView txtDesc = (TextView) view.findViewById(R.id.txtDescripcion);

        Historial item = items.get(position);

        txtFecha.setText(item.getFecha());
        txtHora.setText(item.getHora());
        txtTipo.setText(item.getEstAlimenticia());
        txtGlucemia.setText(String.valueOf(item.getGlucemia()));
        txtCarbo.setText(String.valueOf(item.getCarbohidratos()));
        txtInsulina.setText(String.valueOf(item.getInsulina()));
        txtDesc.setText(item.getDescripcion());

        return view;
    }
}
