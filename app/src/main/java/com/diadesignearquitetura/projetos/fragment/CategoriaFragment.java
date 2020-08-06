package com.diadesignearquitetura.projetos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.RecyclerItemClickListener;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.adapter.Adapter_Categoria;
import com.diadesignearquitetura.projetos.model.Categoria;

public class CategoriaFragment extends Fragment {

    private RecyclerView recyclerView;
    private View root;
    private Adapter_Categoria adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set current fragment
        MainActivity.categoriaFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_categoria, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        //configurar adapter
        adapter = new Adapter_Categoria(MainActivity.listaCategorias);

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //configura click no RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(root.getContext(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Categoria cat = MainActivity.listaCategorias.get(position);
                                MainActivity.PopUpCriarCategoria(root, cat);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        //set current fragment
        MainActivity.currentFragment = this;

        return root;
    }

    public void UpdateRecyclerView(){
        adapter.notifyDataSetChanged();
        recyclerView.refreshDrawableState();
    }



}
