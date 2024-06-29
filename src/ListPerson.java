import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListPerson {
    private ArrayList<Person> listPerson;
    public ListPerson(){
        this.listPerson = new ArrayList<>();
    }
    public ListPerson(ArrayList<Person> listPerson){
        this.listPerson = listPerson;
    }
    public void addPerson(Person person){
        this.listPerson.add(person);
    }
    public void addList(ArrayList<Person> listPerson){
        this.listPerson.addAll(listPerson);
    }
    public ArrayList<Person> getListPerson(){
        return this.listPerson;
    }
    public void readPerson(String path){
        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                Person person = new Person(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], Integer.parseInt(data[5]));
                listPerson.add(person);
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public Person find(String input){
        for(Person p : listPerson){
            if((input.length() == 6 && Integer.toString(p.getIdCard()).equals(input)) || (input.length() == 7 && Integer.toString(p.getTel()).equals(input))){
                return p;
            }
        }
        return null;
    }
    public boolean isExist(String input){
        return find(input) != null;
    }

}
