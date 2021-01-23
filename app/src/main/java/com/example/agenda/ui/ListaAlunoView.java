package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunoView {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context contex;

    public ListaAlunoView(Context contex) {
        this.contex = contex;
        this.adapter = new ListaAlunosAdapter(this.contex);
        this.dao=new AlunoDAO();
    }

    public void confirmaRemocao(MenuItem item) {
        new AlertDialog
                .Builder(contex)
                .setTitle("Remover Aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEcolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEcolhido);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listadealunos) {
        listadealunos.setAdapter(adapter);
    }
}
