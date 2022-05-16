package org.example;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<Aluno> listAluno = new ArrayList<Aluno>();
        List<String[]> linhas = new ArrayList<>();
        listAluno.add(new Aluno("Somas", 0, 0));
        listAluno.add(new Aluno("Kleiton Rodrigues", 25, 9));
        listAluno.add(new Aluno("Renato Silva", 28, 8));
        listAluno.add(new Aluno("Joao Silva", 30, 10));

        double somaNotas = 0;
        int somaIdade = 0;

        for (Aluno aluno : listAluno){
            somaNotas += aluno.getNota();
            somaIdade += aluno.getIdade();

        }
        System.out.println("somaNotas = " + somaNotas);
        System.out.println("somaIdade = " + somaIdade);
        listAluno.get(0).setIdade(somaIdade);
        listAluno.get(0).setNota(somaNotas);

        FileWriter writer = new FileWriter("relatorio.csv");
        StatefulBeanToCsv<Aluno> beanToCsv = new StatefulBeanToCsvBuilder<Aluno>(writer).withSeparator(';').build();

        beanToCsv.write(listAluno);

        writer.flush();
        writer.close();
    }
}