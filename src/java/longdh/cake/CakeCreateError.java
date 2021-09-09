/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.cake;

/**
 *
 * @author Dong Long
 */
public class CakeCreateError {

    private String cakenameErr;
    private String cakepriceErr;
    private String quantityErr;
    private String categoriIDErr;
    private String descriptionErr;
    private String imageErr;
    private String expriraDateErr;

    public CakeCreateError() {
    }

    public CakeCreateError(String cakenameErr, String cakepriceErr, String quantityErr, String categoriIDErr, String descriptionErr, String imageErr, String expriraDateErr) {
        this.cakenameErr = cakenameErr;
        this.cakepriceErr = cakepriceErr;
        this.quantityErr = quantityErr;
        this.categoriIDErr = categoriIDErr;
        this.descriptionErr = descriptionErr;
        this.imageErr = imageErr;
        this.expriraDateErr = expriraDateErr;
    }


    public String getCakenameErr() {
        return cakenameErr;
    }

    public void setCakenameErr(String cakenameErr) {
        this.cakenameErr = cakenameErr;
    }

    public String getCakepriceErr() {
        return cakepriceErr;
    }

    public void setCakepriceErr(String cakepriceErr) {
        this.cakepriceErr = cakepriceErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getCategoriIDErr() {
        return categoriIDErr;
    }

    public void setCategoriIDErr(String categoriIDErr) {
        this.categoriIDErr = categoriIDErr;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    public void setDescriptionErr(String descriptionErr) {
        this.descriptionErr = descriptionErr;
    }

    public String getImageErr() {
        return imageErr;
    }

    public void setImageErr(String imageErr) {
        this.imageErr = imageErr;
    }

    public String getExpriraDateErr() {
        return expriraDateErr;
    }

    public void setExpriraDateErr(String expriraDateErr) {
        this.expriraDateErr = expriraDateErr;
    }





}
