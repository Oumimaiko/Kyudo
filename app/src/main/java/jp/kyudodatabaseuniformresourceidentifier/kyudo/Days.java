package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Days extends RealmObject implements Serializable {
    private Date date;
    private int totalArrows;
    private int hitArrows;

    @PrimaryKey
    private int id;

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getTotalArrows(){
        return totalArrows;
    }

    public void setTotalArrows(int totalArrows){
        this.totalArrows = totalArrows;
    }

    public int getHitArrows(){
        return hitArrows;
    }

    public void setHitArrows(int hitArrows){
        this.hitArrows = hitArrows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
