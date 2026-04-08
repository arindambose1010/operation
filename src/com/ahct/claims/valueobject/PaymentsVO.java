package com.ahct.claims.valueobject ;

import java.io.Serializable;
import java.util.List ;
/**
 * @author Ishank Paharia
 * @class This Class is used as a ValueObject in claim payment. 
 * @version jdk 1.6
 * @Date  4 November 2013
 */
public class PaymentsVO implements Serializable
{
  private static final String EMPTY = "" ;
  private static final double ZERO_VAL = 0 ;
  private String uniqueId ;
  private String beneficiaryAccName ;
  private String beneficiaryId ;
  private String beneficiaryAddr ;
  private String beneficiaryBankName ;
  private String bankBranch ;
  private String beneficiaryAccNo ;
  private double transactionAmount ;
  private String beneficiaryBankId ;
  private String beneficiaryBankIFCCode ;
  private String claintACcode ;
  private String claintACNo ;
  private String bankCategory ;
  private String transactionType ;
  private String emailId ;
  private String srcACCTNO ;
  private String desACCTNO ;
  private double pnldocAmt ;
  private double tdsAmt ;
  private double totalClAmt ;
  private String paymentStatus ;
  private double tdsSurAmt ;
  private double tdsCessAmt ;
  private double tdsTaxAmt ;
  private String tdsflag;
  private List PaymentsList ;

  public PaymentsVO ()
  {
    this.uniqueId = EMPTY ;
    this.beneficiaryAccName = EMPTY ;
    this.beneficiaryId = EMPTY ;
    this.beneficiaryAddr = EMPTY ;
    this.beneficiaryBankName = EMPTY ;
    this.bankBranch = EMPTY ;
    this.beneficiaryAccNo = EMPTY ;
    this.transactionAmount = ZERO_VAL ;
    this.beneficiaryBankId = EMPTY ;
    this.beneficiaryBankIFCCode = EMPTY ;
    this.claintACcode = EMPTY ;
    this.claintACNo = EMPTY ;
    this.bankCategory = EMPTY ;
    this.transactionType = EMPTY ;
    this.emailId = EMPTY ;
    this.srcACCTNO = EMPTY ;
    this.desACCTNO = EMPTY ;
    this.pnldocAmt = ZERO_VAL ;
    this.tdsAmt = ZERO_VAL ;
    this.paymentStatus = EMPTY ;
    this.tdsSurAmt = ZERO_VAL ;
    this.tdsCessAmt = ZERO_VAL ;
    this.tdsTaxAmt = ZERO_VAL ;
    this.PaymentsList = null ;
    this.tdsflag = EMPTY ;
  }

  public void setUniqueId ( String uniqueId )
  {
    this.uniqueId = uniqueId ;
  }

  public String getUniqueId ()
  {
    return uniqueId ;
  }

  public void setBeneficiaryAccName ( String beneficiaryAccName )
  {
    this.beneficiaryAccName = beneficiaryAccName ;
  }

  public String getBeneficiaryAccName ()
  {
    return beneficiaryAccName ;
  }

  public void setBeneficiaryId ( String beneficiaryId )
  {
    this.beneficiaryId = beneficiaryId ;
  }

  public String getBeneficiaryId ()
  {
    return beneficiaryId ;
  }

  public void setBeneficiaryAddr ( String beneficiaryAddr )
  {
    this.beneficiaryAddr = beneficiaryAddr ;
  }

  public String getBeneficiaryAddr ()
  {
    return beneficiaryAddr ;
  }

  public void setBeneficiaryBankName ( String beneficiaryBankName )
  {
    this.beneficiaryBankName = beneficiaryBankName ;
  }

  public String getBeneficiaryBankName ()
  {
    return beneficiaryBankName ;
  }

  public void setBankBranch ( String bankBranch )
  {
    this.bankBranch = bankBranch ;
  }

  public String getBankBranch ()
  {
    return bankBranch ;
  }

  public void setBeneficiaryAccNo ( String beneficiaryAccNo )
  {
    this.beneficiaryAccNo = beneficiaryAccNo ;
  }

  public String getBeneficiaryAccNo ()
  {
    return beneficiaryAccNo ;
  }

  public void setTransactionAmount ( double transactionAmount )
  {
    this.transactionAmount = transactionAmount ;
  }

  public double getTransactionAmount ()
  {
    return transactionAmount ;
  }
  public void setTotalClaimAmt ( double totalClAmt )
  {
    this.totalClAmt = totalClAmt ;
  }

  public double getTotalClaimAmt ()
  {
    return totalClAmt ;
  }

  public void setBeneficiaryBankId ( String beneficiaryBankId )
  {
    this.beneficiaryBankId = beneficiaryBankId ;
  }

  public String getBeneficiaryBankId ()
  {
    return beneficiaryBankId ;
  }

  public void setBeneficiaryBankIFCCode ( String beneficiaryBankIFCCode )
  {
    this.beneficiaryBankIFCCode = beneficiaryBankIFCCode ;
  }

  public String getBeneficiaryBankIFCCode ()
  {
    return beneficiaryBankIFCCode ;
  }

  public void setClaintACcode ( String claintACcode )
  {
    this.claintACcode = claintACcode ;
  }

  public String getClaintACcode ()
  {
    return claintACcode ;
  }

  public void setClaintACNo ( String claintACNo )
  {
    this.claintACNo = claintACNo ;
  }

  public String getClaintACNo ()
  {
    return claintACNo ;
  }

  public void setTransactionType ( String transactionType )
  {
    this.transactionType = transactionType ;
  }

  public String getTransactionType ()
  {
    return transactionType ;
  }

  public void setEmailId ( String emailId )
  {
    this.emailId = emailId ;
  }

  public String getEmailId ()
  {
    return emailId ;
  }

  public void setSrcACCTNO ( String srcACCTNO )
  {
    this.srcACCTNO = srcACCTNO ;
  }

  public String getSrcACCTNO ()
  {
    return srcACCTNO ;
  }

  public void setDesACCTNO ( String desACCTNO )
  {
    this.desACCTNO = desACCTNO ;
  }

  public String getDesACCTNO ()
  {
    return desACCTNO ;
  }

  public void setPaymentStatus ( String paymentStatus )
  {
    this.paymentStatus = paymentStatus ;
  }

  public String getPaymentStatus ()
  {
    return paymentStatus ;
  }

  public void setPnldocAmt ( double pnldocAmt )
  {
    this.pnldocAmt = pnldocAmt ;
  }

  public double getPnldocAmt ()
  {
    return pnldocAmt ;
  }

  public void setTdsAmt ( double tdsAmt )
  {
    this.tdsAmt = tdsAmt ;
  }

  public double getTdsAmt ()
  {
    return tdsAmt ;
  }

  public void setTdsSurAmt ( double tdsSurAmt )
  {
    this.tdsSurAmt = tdsSurAmt ;
  }

  public double getTdsSurAmt ()
  {
    return tdsSurAmt ;
  }

  public void setTdsCessAmt ( double tdsCessAmt )
  {
    this.tdsCessAmt = tdsCessAmt ;
  }

  public double getTdsCessAmt ()
  {
    return tdsCessAmt ;
  }

  public void setTdsTaxAmt ( double tdsTaxAmt )
  {
    this.tdsTaxAmt = tdsTaxAmt ;
  }

  public double getTdsTaxAmt ()
  {
    return tdsTaxAmt ;
  }
  public void setBankCategory ( String bankCategory )
  {
    this.bankCategory = bankCategory ;
  }

  public String getBankCategory ()
  {
    return bankCategory ;
  }
  public void setPayments ( List PaymentsList )
  {
    this.PaymentsList = PaymentsList ;
  }
  public List getPayments ()
  {
    return PaymentsList ;
  }
  public void setTdsflag ( String tdsflag )
  {
    this.tdsflag = tdsflag;
  }
  public String getTdsflag ()
  {
    return tdsflag ;
  }
}
