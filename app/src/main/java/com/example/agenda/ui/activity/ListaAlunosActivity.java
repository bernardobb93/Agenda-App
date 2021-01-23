package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.R;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.ListaAlunoView;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String SET_APPBAR = "Lista de Alunos";
    public final AlunoDAO dao = new AlunoDAO();
    private ListaAlunosAdapter adapter;
    private final ListaAlunoView listaAlunoView = new ListaAlunoView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(SET_APPBAR);
        setContentView(R.layout.activity_main);
        configuraNovoAluno();
        configuralista(dao);
        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunoView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoAluno = findViewById(R.id.floatingActionButton4);
        botaoAluno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        listaAlunoView.atualizaAlunos();
    }

    private void configuralista(AlunoDAO dao) {
        ListView listadealunos = findViewById(R.id.activity_main_lista_de_alunos);
        listaAlunoView.configuraAdapter(listadealunos);
        configuraListenerdeCliquePorItem(listadealunos);
        registerForContextMenu(listadealunos);
    }

    private void configuraListenerdeCliquePorItem(ListView listadealunos) {
        listadealunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoInsereAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoInsereAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

}
