package com.diadesignearquitetura.projetos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.RecyclerItemClickListener;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.adapter.Adapter_Acao;
import com.diadesignearquitetura.projetos.model.Acao;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcaoFragment extends Fragment {

    private View root;
    private RecyclerView recyclerView;
    private Adapter_Acao adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set current fragment
        MainActivity.acaoFragment = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_acao, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        //configurar adapter
        adapter = new Adapter_Acao(MainActivity.listaAcoes);

        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //configura clique no RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(root.getContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Acao acao = MainActivity.listaAcoes.get(position);
                        MainActivity.PopUpCriarAcao(root, acao);
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

        return root;
    }

    public void UpdateRecyclerView(){
        adapter.notifyDataSetChanged();
        recyclerView.refreshDrawableState();
    }
}
