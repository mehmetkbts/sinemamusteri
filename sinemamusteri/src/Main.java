import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

class BaseEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

interface IKayit {
    void kaydet();
    void sil();
}

class Salon {
    private String salonAdi;
    private List<String> koltuklar; // Koltuk numaraları
    private Map<String, Boolean> koltukDurumu; // Koltuk durumu: true = dolu, false = boş

    public Salon(String salonAdi, List<String> koltuklar) {
        this.salonAdi = salonAdi;
        this.koltuklar = koltuklar;
        this.koltukDurumu = new HashMap<>();
        for (String koltuk : koltuklar) {
            koltukDurumu.put(koltuk, false); // Başlangıçta tüm koltuklar boş
        }
    }

    public String getSalonAdi() {
        return salonAdi;
    }

    public boolean koltukSec(String koltukNumarasi) {
        if (koltukDurumu.containsKey(koltukNumarasi) && !koltukDurumu.get(koltukNumarasi)) {
            koltukDurumu.put(koltukNumarasi, true); // Koltuk dolu oldu
            return true; // Koltuk başarıyla seçildi
        }
        return false; // Koltuk zaten dolu
    }

    public void koltukDurumunuGoster() {
        System.out.println("Koltuk Durumu: ");
        for (Map.Entry<String, Boolean> entry : koltukDurumu.entrySet()) {
            System.out.println("Koltuk: " + entry.getKey() + " - " + (entry.getValue() ? "Dolu" : "Boş"));
        }
    }
}

class Musteri extends BaseEntity implements IKayit {
    private String isim;
    private String soyisim;
    private String yemek; // Müşterinin yemek tercihi
    private Film film;
    private Seans seans;
    private String secilenKoltuk;

    public Musteri(String id, String isim, String soyisim, String yemek) {
        this.setId(id);
        this.isim = isim;
        this.soyisim = soyisim;
        this.yemek = yemek;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getYemek() {
        return yemek;
    }

    public void setYemek(String yemek) {
        this.yemek = yemek;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Seans getSeans() {
        return seans;
    }

    public void setSeans(Seans seans) {
        this.seans = seans;
    }

    public void yemekSecimi() {
        if (yemek.equalsIgnoreCase("Evet")) {
            System.out.println("Yemek Seçenekleri:");
            System.out.println("1. Mısır");
            System.out.println("2. Kola");
            System.out.print("Seçiminizi yapın: ");
            Scanner scanner = new Scanner(System.in);
            int yemekSecimi = scanner.nextInt();
            if (yemekSecimi == 1) {
                this.yemek = "Mısır";
            } else if (yemekSecimi == 2) {
                this.yemek = "Kola";
            }
        }
    }

    public void filmSecimi(List<Film> filmler) {
        System.out.println("Filmler:");
        for (int i = 0; i < filmler.size(); i++) {
            System.out.println((i + 1) + ". " + filmler.get(i).getAd());
        }
        System.out.print("Bir film seçin (1-3): ");
        Scanner scanner = new Scanner(System.in);
        int secim = scanner.nextInt();
        this.film = filmler.get(secim - 1);
        System.out.println("Seçilen Film: " + film.getAd());
    }

    public void koltukSecimi(Salon salon) {
        salon.koltukDurumunuGoster();
        System.out.print("Bir koltuk numarası seçin: ");
        Scanner scanner = new Scanner(System.in);
        String koltukNumarasi = scanner.nextLine();
        if (salon.koltukSec(koltukNumarasi)) {
            this.secilenKoltuk = koltukNumarasi;
            System.out.println("Koltuk seçimi başarılı: " + koltukNumarasi);
        } else {
            System.out.println("Seçilen koltuk dolu, lütfen başka bir koltuk seçin.");
            koltukSecimi(salon); // Tekrar denemek için recursive çağrı
        }
    }

    public void kaydet() {
        Gson gson = new Gson();
        List<Musteri> musteriler = Musteri.readFromJson();
        musteriler.add(this);
        try (FileWriter writer = new FileWriter("Musteri.json")) {
            gson.toJson(musteriler, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JSON formatında konsola yazdırma
        System.out.println("Müşteri Kaydı JSON:");
        System.out.println(gson.toJson(this));
    }

    public void sil() {
        Gson gson = new Gson();
        List<Musteri> musteriler = Musteri.readFromJson();
        musteriler.removeIf(musteri -> musteri.getId().equals(this.getId()));
        try (FileWriter writer = new FileWriter("Musteri.json")) {
            gson.toJson(musteriler, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Musteri> readFromJson() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("Musteri.json")) {
            return gson.fromJson(reader, new TypeToken<List<Musteri>>() {}.getType());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

class Film extends BaseEntity {
    private String ad;
    private int sure;
    private String tur;

    public Film(String id, String ad, int sure, String tur) {
        this.setId(id);
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    // Bilet fiyatı hesaplama
    public int fiyatHesapla(String yemek) {
        int baseFiyat = 50; // Temel bilet fiyatı

        if (this.tur.equalsIgnoreCase("Aksiyon")) {
            baseFiyat += 20; // Aksiyon filmleri daha pahalı
        } else if (this.tur.equalsIgnoreCase("Bilim Kurgu")) {
            baseFiyat += 15;
        }

        if (yemek.equalsIgnoreCase("Mısır")) {
            baseFiyat += 10;
        } else if (yemek.equalsIgnoreCase("Kola")) {
            baseFiyat += 5;
        }

        return baseFiyat;
    }
}

class Seans {
    public Salon salon;
    private String saat;
    Film film;
    private Salon seans;

    public Seans(String saat, Film film, Salon salon) {
        this.saat = saat;
        this.film = film;
        this.salon = salon;
    }

    public void salonSecimi() {
        System.out.println("Salonlar:");
        System.out.println("1. Salon A");
        System.out.println("2. Salon B");
        System.out.println("3. Salon C");
        System.out.print("Seçiminizi yapın: ");
        Scanner scanner = new Scanner(System.in);
        int secim = scanner.nextInt();
        scanner.nextLine(); // Temizleme
        if (secim == 1) {
            this.salon = new Salon("A", Arrays.asList("A1", "A2", "A3", "A4", "A5"));
        } else if (secim == 2) {
            this.salon = new Salon("B", Arrays.asList("B1", "B2", "B3", "B4", "B5"));
        } else if (secim == 3) {
            this.salon = new Salon("C", Arrays.asList("C1", "C2", "C3", "C4", "C5"));
        }
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Film verileri
        Film film1 = new Film("1", "Avengers", 120, "Aksiyon");
        Film film2 = new Film("2", "Batman", 150, "Aksiyon");
        Film film3 = new Film("3", "Matrix", 135, "Bilim Kurgu");

        // Filmleri Film.json dosyasına kaydetme
        List<Film> filmler = new ArrayList<>();
        filmler.add(film1);
        filmler.add(film2);
        filmler.add(film3);

        // Salonlar ve seanslar
        Salon salonA = new Salon("A", Arrays.asList("A1", "A2", "A3", "A4", "A5"));
        Salon salonB = new Salon("B", Arrays.asList("B1", "B2", "B3", "B4", "B5"));
        Salon salonC = new Salon("C", Arrays.asList("C1", "C2", "C3", "C4", "C5"));

        Seans seans1 = new Seans("10:00", film1, salonA);
        Seans seans2 = new Seans("14:00", film2, salonB);
        Seans seans3 = new Seans("18:00", film3, salonC);

        // Menü ile kullanıcıya seçenek sunma
        System.out.println("Sinema Müşteri Kayıt Sistemi");
        System.out.print("Adınızı girin: ");
        String isim = scanner.nextLine();
        System.out.print("Soyadınızı girin: ");
        String soyisim = scanner.nextLine();
        System.out.print("Yemek almak istiyor musunuz? (Evet/Hayır): ");
        String yemek = scanner.nextLine();

        // Müşteri oluşturma
        Musteri musteri = new Musteri("1", isim, soyisim, yemek);
        musteri.yemekSecimi();

        // Film seçimi
        musteri.filmSecimi(filmler);

        // Salon seçimi
        seans1.salonSecimi();
        musteri.koltukSecimi(seans1.salon);

        // Bilet fiyatını hesaplama
        int fiyat = seans1.film.fiyatHesapla(musteri.getYemek());
        System.out.println("Toplam Ücret: " + fiyat);

        // Müşteri kaydı
        musteri.kaydet();

        // Çıkış seçeneği
        System.out.println("Çıkmak için herhangi bir tuşa basın...");
        scanner.nextLine(); // Kullanıcının çıkış yapması için bekle
    }
}

