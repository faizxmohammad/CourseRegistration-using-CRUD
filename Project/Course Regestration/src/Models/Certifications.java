package Models;

public class Certifications {
    private  String certificateId;
    private String certificationName;

    public Certifications() {
    }

    public Certifications(String certificateId, String certificationName) {
        this.certificateId = certificateId;
        this.certificationName = certificationName;
    }

    public String getCertificateId() {
        return certificateId;
    }
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificationName() {
        return certificationName;
    }
    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }


}
