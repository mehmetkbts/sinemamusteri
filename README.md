## Sinema Müşteri Kayıt Sistemi
Bu proje, bir sinema salonu için müşteri kayıt ve bilet satın alma sistemini simüle eden bir Java uygulamasıdır. Kullanıcılar, film seçimi, yemek tercihi, salon ve koltuk seçimi gibi işlemleri gerçekleştirebilir, ardından biletlerini alabilirler. Ayrıca, müşteri bilgileri ve bilet geçmişi JSON formatında kaydedilir.

##Özellikler
1)Müşteri Kaydı: Kullanıcılar, sistemde kayıt olabilmek için ad, soyad ve yemek tercihi gibi bilgileri girebilir.

2)Film Seçimi: Kullanıcılar, mevcut filmler arasından birini seçebilirler. Seçilen filme göre bilet fiyatı hesaplanır.

3)Salon Seçimi: Kullanıcılar, belirli bir film için uygun salonu seçebilirler.

4)Koltuk Seçimi: Seçilen salon içinde boş koltuklardan birini seçebilirler. Koltuk durumu anlık olarak güncellenir.

5)Yemek Seçimi: Kullanıcılar, yemek seçenekleri (Mısır, Kola) arasından tercihler yapabilir.

6)Veri Kaydı ve Okuma: Tüm müşteri verileri JSON formatında kaydedilir ve gerektiğinde okunabilir.


## GSON Yani JSON Kurma
https://mvnrepository.com/artifact/com.google.code.gson/gson bu linkten gson 2.10.1 olanı indirin

## IntelliJ IDEA kullanıyorsan
File > Project Structure > Libraries sekmesine git.
+ Tuşuna basın
Ardından indirdiğiniz jar dosyasını seçin ve Apply OK tuşuna basın ve kütüphanemiz kurulmuş olucak

## Eclipse kullanıyorsan
Proje üzerine sağ tıklayıp Build Path > Add External Archives seçeneğini seç.
İndirilen JAR dosyasını seçip projene dahil et.

## Kullanım

1)Ad ve Soyad Girişi: Kullanıcıdan ad ve soyad istenir.

2)Yemek Seçimi: Yemek tercihi sorulur. Eğer "Evet" yanıtı verilirse, yemek seçenekleri sunulur (Mısır veya Kola).

3)Film Seçimi: Mevcut filmler listesinden bir film seçilir.

4)Salon Seçimi: Kullanıcı, filmin gösterileceği salonu seçer. Mevcut salonlar A, B, ve C olarak belirlenmiştir.

5)Koltuk Seçimi: Seçilen salondaki boş koltuklar gösterilir ve kullanıcı bir koltuk seçer.

6)Bilet Fiyatı Hesaplama: Seçilen film ve yemek tercihlerine göre toplam ücret hesaplanır ve kullanıcıya gösterilir.

7)Müşteri Kaydı: Müşteri bilgileri JSON formatında kaydedilir.

8)Çıkış: Kullanıcı, işlem sonrasında çıkış yapabilir.

## JSON Veri Yapısı

## Müşteri JSON
Her müşteri, aşağıdaki bilgileri içerir:

json
{
  "id": "1",
  "isim": "Ahmet",
  "soyisim": "Yılmaz",
  "yemek": "Mısır",
  "film": {
    "id": "1",
    "ad": "Avengers",
    "sure": 120,
    "tur": "Aksiyon"
  },
  "seans": {
    "saat": "10:00",
    "film": {
      "id": "1",
      "ad": "Avengers",
      "sure": 120,
      "tur": "Aksiyon"
    },
    "salon": {
      "salonAdi": "A",
      "koltuklar": ["A1", "A2", "A3", "A4", "A5"],
      "koltukDurumu": {
        "A1": false,
        "A2": true,
        "A3": false,
        "A4": false,
        "A5": false
      }
    }
  },
  "secilenKoltuk": "A1"
}

## Film JSON
Her film, aşağıdaki bilgileri içerir:

json

{
  "id": "1",
  "ad": "Avengers",
  "sure": 120,
  "tur": "Aksiyon"
}

## Salon JSON Yapısı
Salon, koltuklar ve koltukların doluluk durumunu içerir. Salon JSON'u şu şekilde olabilir:

json
{
  "salonAdi": "A",
  "koltuklar": ["A1", "A2", "A3", "A4", "A5"],
  "koltukDurumu": {
    "A1": false,
    "A2": true,
    "A3": false,
    "A4": false,
    "A5": false
  }
}

## Seans JSON Yapısı
Seans, belirli bir film için zaman ve salon bilgilerini içerir. Seans JSON'u şu şekilde olabilir:

json
{
  "saat": "10:00",
  "film": {
    "id": "1",
    "ad": "Avengers",
    "sure": 120,
    "tur": "Aksiyon"
  },
  "salon": {
    "salonAdi": "A",
    "koltuklar": ["A1", "A2", "A3", "A4", "A5"],
    "koltukDurumu": {
      "A1": false,
      "A2": true,
      "A3": false,
      "A4": false,
      "A5": false
    }
  }
}

## Lisans
https://github.com/mehmetkbts
