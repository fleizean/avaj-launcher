# Avaj Launcher - Java Aircraft Simulation

## 📋 Proje Hakkında

Bu proje, 42 School'un Java öğrenme serilerinin ilk projesidir. Bir havalimanının weather tower sistemi simülasyonunu yapar ve çeşitli design pattern'leri kullanarak Object-Oriented Programming prensiplerini öğretir.

## 🎯 Hedefler

- **UML Class Diagram** okuma ve implement etme
- **Design Patterns** kullanma (Observer, Singleton, Factory)
- **Java OOP** kavramlarını pratikte uygulama
- Clean code yazma ve maintainable architecture kurma

## 🏗️ Design Patterns Açıklamaları

### 1. Observer Pattern
**Ne işe yarar?** Bir nesne durumu değiştiğinde, ona bağlı diğer nesneleri otomatik olarak bilgilendirir.

**Projede kullanımı:**
- `WeatherTower` = **Subject** (Gözlenen)
- `Aircraft`'lar = **Observer** (Gözleyici)
- Weather değiştiğinde, tüm aircraft'lar bilgilendirilir

```
WeatherTower
├── register(aircraft)
├── unregister(aircraft)  
└── notifyObservers() → Tüm aircraft'lara haber ver

Aircraft implements Flyable
└── updateConditions() → Weather değiştiğinde çağrılır
```

### 2. Singleton Pattern
**Ne işe yarar?** Bir sınıftan sadece tek instance oluşturulmasını garanti eder.

**Projede kullanımı:**
- `WeatherProvider` sınıfı Singleton olacak
- Tüm sistem aynı weather provider'ı kullanmalı

```java
public class WeatherProvider {
    private static WeatherProvider instance;
    
    private WeatherProvider() {} // Private constructor
    
    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }
}
```

### 3. Factory Pattern
**Ne işe yarar?** Nesne yaratma işlemini soyutlar, hangi concrete class'ın yaratılacağını runtime'da belirler.

**Projede kullanımı:**
- `AircraftFactory` class'ı
- Type string'ine göre doğru aircraft tipini yaratır

```java
public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, Coordinates coordinates) {
        switch(type.toLowerCase()) {
            case "balloon": return new Baloon(name, coordinates);
            case "jetplane": return new JetPlane(name, coordinates);
            case "helicopter": return new Helicopter(name, coordinates);
            default: throw new InvalidAircraftException();
        }
    }
}
```

## 🗂️ UML Diyagram Analizi

### Ana Bileşenler:

1. **Flyable Interface**
   - `updateConditions()` - Weather değiştiğinde çağrılır
   - `registerTower(WeatherTower)` - Tower'a kayıt ol

2. **Aircraft (Abstract Class)**
   - `id`, `name`, `coordinates` - Ortak özellikler
   - Concrete class'lar: `JetPlane`, `Helicopter`, `Baloon`

3. **WeatherTower**
   - Observer pattern'in Subject'i
   - Aircraft'ları register/unregister eder
   - Weather değişimlerini broadcast eder

4. **WeatherProvider (Singleton)**
   - `getCurrentWeather(Coordinates)` - Koordinata göre weather döner
   - 4 weather type: SUN, RAIN, FOG, SNOW

5. **Coordinates**
   - `longitude`, `latitude`, `height`
   - 3D pozisyon bilgisi

## 🚁 Aircraft Davranışları

### JetPlane:
- **SUN**: Latitude +10, Height +2
- **RAIN**: Latitude +5
- **FOG**: Latitude +1  
- **SNOW**: Height -7

### Helicopter:
- **SUN**: Longitude +10, Height +2
- **RAIN**: Longitude +5
- **FOG**: Longitude +1
- **SNOW**: Height -12

### Baloon:
- **SUN**: Longitude +2, Height +4
- **RAIN**: Height -5
- **FOG**: Height -3
- **SNOW**: Height -15

## 📁 Önerilen Package Yapısı

```
src/
└── ro/academyplus/avaj/
    ├── simulator/
    │   ├── Simulator.java (main class)
    │   └── exceptions/
    │       └── InvalidAircraftException.java
    ├── aircraft/
    │   ├── Flyable.java
    │   ├── Aircraft.java
    │   ├── JetPlane.java
    │   ├── Helicopter.java
    │   ├── Baloon.java
    │   └── AircraftFactory.java
    ├── weather/
    │   ├── WeatherProvider.java
    │   └── WeatherTower.java
    └── coordinates/
        └── Coordinates.java
```

## 🔄 Program Akışı

1. **Başlangıç**: Scenario dosyası okunur
2. **Parsing**: Aircraft'lar yaratılır (Factory pattern)
3. **Registration**: Aircraft'lar tower'a kayıt olur (Observer pattern)
4. **Simulation**: Her döngüde:
   - Weather değişir
   - Tower tüm aircraft'lara bildirir
   - Her aircraft pozisyonunu günceller
   - Mesajlar loglanır
   - Height 0 olanlar landing yapar

## 📝 Scenario Dosyası Format

```
25                          ← Simulation sayısı
Baloon B1 2 3 20          ← TYPE NAME LONGITUDE LATITUDE HEIGHT
JetPlane J1 23 44 32
Helicopter H1 654 33 20
```

## 🎯 Implementation Sırası

1. ✅ **Coordinates** - En basit sınıf
2. ✅ **WeatherProvider** - Singleton pattern
3. ✅ **Flyable interface** - Aircraft contract
4. ✅ **Aircraft abstract class** - Ortak özellikler
5. ✅ **Concrete aircrafts** - JetPlane, Helicopter, Baloon
6. ✅ **AircraftFactory** - Factory pattern
7. ✅ **WeatherTower** - Observer pattern (en karmaşık)
8. ✅ **Simulator** - Main class, everything together

## 🧪 Test Stratejisi

- Basit scenario ile başla
- Edge case'leri test et:
  - Height 0'a düşen aircraft
  - Invalid input files
  - Boundary conditions (height 100+)

## 💡 İpuçları

- **UML'yi takip et** - Access modifier'ları değiştirme
- **Clean code yaz** - Readable ve maintainable olsun  
- **Design pattern'leri doğru uygula** - Bu projenin ana amacı
- **Error handling** ekle - Robust bir sistem yap
- **Funny messages** kullan - Aircraft mesajlarında yaratıcı ol

## 📚 Öğrenilecek Kavramlar

- **Interfaces vs Abstract Classes**
- **Composition vs Inheritance** 
- **Dependency Injection**
- **Separation of Concerns**
- **SOLID Principles**
- **Java Package Management**

---

**Not**: Bu proje sadece kod yazmak değil, **software design** öğrenme projesidir. Pattern'leri ve OOP prensiplerini anlamaya odaklan!