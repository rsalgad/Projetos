package com.diadesignearquitetura.projetos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.RecyclerItemClickListener;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.adapter.Adapter_Projeto;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private View root;
    private Adapter_Projeto adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.firstFragment = this;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment and store it in root
        root = inflater.inflate(R.layout.fragment_first, container, false);

        //get RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);

        //configurar adapter
        adapter = new Adapter_Projeto(MainActivity.listaProjetos);

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //configura click no RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(root.getContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        //set current fragment
        MainActivity.currentFragment = this;

        //return root
        return root;
    }

    public void UpdateRecyclerView(){
        adapter.notifyDataSetChanged();
        recyclerView.refreshDrawableState();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
