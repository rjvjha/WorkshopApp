package com.example.rjvjha.workshop.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rjvjha.workshop.MainActivity;
import com.example.rjvjha.workshop.R;
import com.example.rjvjha.workshop.Utils.AuthUtils;
import com.example.rjvjha.workshop.data.WorkshopContract;
import com.example.rjvjha.workshop.fragments.LoginScreenFragment;
import com.example.rjvjha.workshop.fragments.WorkshopsScreenFragment;

public class WorkshopsAdapter extends RecyclerView.Adapter<WorkshopsAdapter.WorkshopViewHolder>{


    private Context mContext;
    private Cursor mCursor;
    private int fragmentId;
    private WorkshopsScreenFragment mFragment;

    public WorkshopsAdapter(Context context, int id, WorkshopsScreenFragment fragment){
        this.mContext = context;
        this.fragmentId = id;
        this.mFragment = fragment;

    }


    @Override
    public WorkshopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        int itemLayoutId = R.layout.workshop_item_view;
        View itemView = inflater.inflate(itemLayoutId, parent,false);
        return new WorkshopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkshopViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        // getValues from the cursor
        int id = mCursor.getInt(mCursor.getColumnIndex(WorkshopContract.WorkshopEntry.COL_ID));
        String title = mCursor.getString(mCursor.getColumnIndex(WorkshopContract.WorkshopEntry.COL_TITLE));
        String date = mCursor.getString(mCursor.getColumnIndex(WorkshopContract.WorkshopEntry.COL_DATE));
        String description = mCursor.getString(mCursor.getColumnIndex(WorkshopContract.WorkshopEntry.COL_DESCP));
        int apply_status = mCursor.getInt(mCursor.getColumnIndex(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS));

        //bind data to views

        holder.decorator.setText(getFirstCharacter(title));
        holder.title.setText(title);
        holder.date.setText(date);
        holder.description.setText(description);




        // code to hide/show apply button in Fragments Screens
        if(fragmentId == MainActivity.WORKSHOP_SCREEN_ID){
            holder.applyButton.setVisibility(View.VISIBLE);
            holder.onClickApplyButton(id, apply_status);
        } else{
            holder.applyButton.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
            return 0;
        }
        return mCursor.getCount();
    }

    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    // private helper method to get first character for text decorator
    private String getFirstCharacter(String title){
        
        return String.valueOf(title.charAt(0));

    }





    // ViewHolder class
    class WorkshopViewHolder extends RecyclerView.ViewHolder{

        TextView decorator;
        TextView title;
        TextView date;
        TextView description;
        Button applyButton;


        WorkshopViewHolder(View itemView){
            super(itemView);
            decorator = itemView.findViewById(R.id.text_decorator);
            title = itemView.findViewById(R.id.tile_textView);
            date = itemView.findViewById(R.id.date_textView);
            description = itemView.findViewById(R.id.description_textView);
            applyButton = itemView.findViewById(R.id.apply_button);
        }

        // method to handle onClick applyButton
        void onClickApplyButton(final int id, final int status){

            //if already applied then change button text to applied
            if(status == WorkshopContract.WorkshopEntry.APPLIED_TRUE
                    && AuthUtils.isUserLoggedIn(mContext)){
                applyButton.setText(R.string.applied_text_button);
            }else {
                applyButton.setText(R.string.apply_text_button);
            }


            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(AuthUtils.isUserLoggedIn(mContext)){
                        //user is logged-in
                        if(status == 1){
                            // already registered
                            Toast.makeText(mContext, "Already registered!",
                                    Toast.LENGTH_SHORT).show();

                        }else if(status == 0) {
                            // register in db
                            ContentValues cv = new ContentValues();
                            cv.put(WorkshopContract.WorkshopEntry.COL_APPLY_STATUS,
                                    WorkshopContract.WorkshopEntry.APPLIED_TRUE);

                            if(mFragment.registerUserInWorkshop(id,
                                    mContext, cv) > 0){
                                Toast.makeText(mContext, "Successfully applied!", Toast.LENGTH_SHORT).show();
                            }

                        }

                    } else {
                        // user-not logged in
                        Toast.makeText(mContext,"Login to apply!", Toast.LENGTH_SHORT).show();
                        mFragment.openFragmentScreen(new LoginScreenFragment());

                    }


                }
            });


        }


    }
}