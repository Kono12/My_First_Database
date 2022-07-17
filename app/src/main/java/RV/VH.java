package RV;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.my_first_database.R;

public class VH extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name;
    TextView number;
    ConstraintLayout constraintLayout;

    public VH(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.TextView);
        number = itemView.findViewById(R.id.number);
        constraintLayout=itemView.findViewById(R.id.constraint);
    }


}
