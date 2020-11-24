package com.example.agenda;

import android.app.Application;

import com.example.agenda.DAO.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunoTeste();
    }

    private void criarAlunoTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Bernardo","995980992","bernardobb.eletronica@gmail.com"));
    }
}
