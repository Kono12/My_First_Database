package RV;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.my_first_database.R;

import java.util.ArrayList;
import java.util.List;

public class Adp extends RecyclerView.Adapter<VH> {
    ArrayList<DATA> arrayList;
    private OnUserClicked onUserClicked;

    public interface OnUserClicked
    {
        void OnUserLongClicked(DATA user);
    }

    public void register(OnUserClicked onUserClicked){this.onUserClicked=onUserClicked;}



    public  Adp (ArrayList<DATA> arrayList)
    {this.arrayList=arrayList;}

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
    DATA d1 = arrayList.get(position);
    holder.img.setImageResource(d1.getImage());
    holder.name.setText(d1.getName());
    holder.number.setText(d1.getNumber());

        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onUserClicked.OnUserLongClicked(d1);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public DATA ReturnData(int pos){

        return arrayList.get(pos);
    }
}
