package com.saksham.chatbiz.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.saksham.chatbiz.fragment.CallFragment;
import com.saksham.chatbiz.fragment.ChatFragment;
import com.saksham.chatbiz.fragment.StatusFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new ChatFragment();
        }else if (position == 1){
            return new StatusFragment();

        }else if (position == 2){
            return new CallFragment();
        }
        return new ChatFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        String title = null;
        if (position == 0){
            title = "CHATS";
        }else if (position == 1){
            title = "STATUS";
        }
        else if (position == 2){
            title = "CALLS";
        }
        return title;
    }
}
