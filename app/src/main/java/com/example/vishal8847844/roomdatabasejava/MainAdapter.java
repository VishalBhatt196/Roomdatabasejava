package com.example.vishal8847844.roomdatabasejava;

import android.app.Activity;
import android.app.Dialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
//initialize variable
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //create constructor

public MainAdapter(Activity context,List<MainData> dataList){
    this.context=context;
    this.dataList=dataList;
    notifyDataSetChanged();

}

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    MainData data =dataList.get(position);
    database =RoomDB.getInstance(context);
    holder.textView.setText(data.getText());
    holder.imaedit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainData d=dataList.get(holder.getAdapterPosition());
            int SID=d.getID();
            String sText=d.getText();
            Dialog diolog=new Dialog(context);
            diolog.setContentView(R.layout.dialog_update);
            int width= WindowManager.LayoutParams.MATCH_PARENT;
            int height=WindowManager.LayoutParams.WRAP_CONTENT;
            diolog.getWindow().setLayout(width,height);
            diolog.show();

            EditText editText=diolog.findViewById(R.id.edit_text);
            Button btUpdate=diolog.findViewById(R.id.bt_update);
            editText.setText(sText);
            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    diolog.dismiss();
                    String uText=editText.getText().toString().trim();
                    database.mainDao().update(SID,uText);
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    notifyDataSetChanged();
                }
            });
        }

    });
    holder.imgdelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainData d=dataList.get(holder.getAdapterPosition());
            database.mainDao().delete(d);
            int position=holder.getAdapterPosition();
            dataList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,dataList.size());

        }
    });
}

@Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView textView;
    ImageView imaedit,imgdelete;

        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
            imaedit=itemView.findViewById(R.id.bt_edit);
            imgdelete=itemView.findViewById(R.id.bt_delete);


        }
    }
}
