package id.idn.datasiswa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.idn.datasiswa.ResponseServer.DataItem;

/**
 * Created by hp on 3/25/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    List<DataItem> data;
    public static final String WebUrl = "http://192.168.70.17/phpcrud/";

    public CustomAdapter(Context context, List<DataItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String url_image = WebUrl + "upload/"+data.get(position).getNameimage();
        Log.d("TAG", "" + data.get(position).getAddress());
        holder.nama.setText(data.get(position).getName());
        holder.address.setText("Address : " + data.get(position).getAddress());
        holder.hometown.setText("HomeTown : " + data.get(position).getHometown());
        holder.sex.setText("Gender :" + data.get(position).getSex());
        holder.classs.setText("Class :" +  data.get(position).getJsonMemberClass());
        Glide.with(context)
                .load(url_image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nama, classs, sex, hometown, address;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            classs = itemView.findViewById(R.id.classs);
            sex = itemView.findViewById(R.id.sex);
            hometown = itemView.findViewById(R.id.hometown);
            address = itemView.findViewById(R.id.address);
            image = itemView.findViewById(R.id.image);
        }
    }
}
