import java.util.ArrayList;
import java.util.Date;

public class Bill {
    private int idBill;
    private Date create;
    private ArrayList<Detail> listDetail;
    private double total;

    public Bill(int idBill, Date create, ArrayList<Detail> listDetail, double total){
        this.idBill = idBill;
        this.create = create;
        this.listDetail = listDetail;
        this.total = total;
    }

    public int getIdBill(){
        return idBill;
    }
    public void setIdBill(int idBill){
        this.idBill = idBill;
    }

    public Date getCreate(){
        return create;
    }
    public void setCreate(Date create){
        this.create = create;
    }
    public double getTotal(){
        return total;
    }
    public void setTotal(double total){
        this.total = total;
    }

}
