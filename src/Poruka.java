public class Poruka {
    private byte destinacija;
    private byte id;
    private byte pnam;
    private byte[] poruka;

    public Poruka(byte destinacija, byte id, byte pnam, byte[] poruka) {
        this.destinacija = destinacija;
        this.id = id;
        this.pnam = pnam;
        this.poruka = poruka;
    }

    public byte getDestinacija() {
        return destinacija;
    }

    public void setDestinacija(byte destinacija) {
        this.destinacija = destinacija;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getPnam() {
        return pnam;
    }

    public void setPnam(byte pnam) {
        this.pnam = pnam;
    }

    public byte[] getPoruka() {
        return poruka;
    }

    public void setPoruka(byte[] poruka) {
        this.poruka = poruka;
    }
}
