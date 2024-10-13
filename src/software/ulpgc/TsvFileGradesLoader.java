package software.ulpgc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TsvFileGradesLoader implements GradesLoader {
    private final File file;

    public TsvFileGradesLoader(File file) {
        this.file = file;
    }

    @Override
    public List<Grades> load() {
        try {
            return load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Grades> load(FileReader reader) throws IOException {
        return load(new BufferedReader(reader));
    }

    private List<Grades> load(BufferedReader reader) throws IOException {
        reader.readLine();
        List<Grades> grades = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) return grades;
            grades.add(toGrades(line));

        }
    }

    private Grades toGrades(String line) {
        return toGrades(line.split("\t"));
    }

    private Grades toGrades(String[] split) {
        return new Grades(
                split[0],
                split[1],
                split[2]
        );
    }
}
