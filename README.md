# JWT Token Uygulaması

Bu proje, Spring Security ve JWT (JSON Web Token) kullanarak, rol tabanlı kimlik doğrulama ve yetkilendirme sağlayan basit bir kullanıcı yönetim sistemini içermektedir.

## Rol Tanımları

### Role Enum

- `Role` enum'u, sistemdeki bir rolü temsil eder.
- Önceden tanımlanmış rolleri içerir: USER, ADMIN, MOD.

## Kullanıcı Yapısı

### User Entity

- `User` sınıfı, sistemin bir kullanıcısını temsil eder.
- Kullanıcı adı, şifre, ad, yetkiler ve hesap durumu gibi bilgileri içerir.
  
## Şifreleme

### PasswordEncoderConfig

- `PasswordEncoderConfig`, şifre kodlaması için bir BCryptPasswordEncoder bean sağlar. Bu sayede şifreler güvenli bir şekilde saklanır.

## Güvenlik Yapılandırması

### SecurityConfig

- `SecurityConfig`, Spring Security'nin HTTP Temel kimlik doğrulaması: Kullanıcıların kullanıcı adı ve şifrelerini kullanarak kimlik doğrulamasına olanak tanır.
- Genel ve özel kaynaklara erişim kurallarını belirler ve kullanıcıların kaynaklara erişimini rollere göre kontrol eder.
- Tüm kullanıcıların erişebileceği URL'leri ve kimlik doğrulaması gerektiren URL'leri tanımlar.

## Servisler

### UserService

- `UserService`, kullanıcı yönetimi için iş mantığını sağlar.
- Kullanıcı oluşturma ve kullanıcı adına göre kullanıcı alma gibi yöntemleri içerir.

## JWT Servisi

### JwtService

- `JwtService`, JWT oluşturma, doğrulama ve kullanıcı bilgilerini çıkarma işlemlerini gerçekleştirir.
- JWT'nin geçerliliğini kontrol eder.

## Kullanıcı Kontrol ve Yetkilendirme

### JwtAuthFilter

- `JwtAuthFilter`, gelen her isteği kontrol eder ve JWT ile kimlik doğrulaması yapar.
- Geçerli bir JWT varsa, kullanıcıyı yetkilendirir.

## Uygulama Başlangıcı

### JwtTokenApplication

- Uygulamanın ana başlangıç noktasıdır.
- `main` metodu ile uygulamayı başlatır.

## Kullanım

Projenin yerel ortamınızda çalıştırılabilmesi için aşağıdaki adımları takip edebilirsiniz:

1. **Depoyu Klonlayın:**
    ```bash
    git clone https://github.com/ADBERILGEN35/JwtTokenApplication.git
    ```

2. **Proje Dosyalarını Açın:**
    Proje dosyalarını tercih ettiğiniz IDE veya metin düzenleyici ile açın.

3. **Veritabanı Yapılandırması:**
 Terminalden
    ```bash
    docker-compose up
    ```
    komutu ile docker containers oluşturun. `docker-compose.yml` dosyasında tanımlandığı şekilde veritabanı oluşacaktır.
5. **Uygulamayı Çalıştırın:**
    ```bash
    mvn spring-boot:run
    ```

6. **Kullanım:**
    - Public endpoint için [http://localhost:8080/auth/welcome](http://localhost:8080/auth/welcome)
    - Yeni kullanıcı eklemek için [http://localhost:8080/auth/addNewUser](http://localhost:8080/auth/addNewUser)
    - Token oluşturmak için [http://localhost:8080/auth/generateToken](http://localhost:8080/auth/generateToken)
    - Kullanıcı rolü ile erişim için [http://localhost:8080/auth/user](http://localhost:8080/auth/user)
    - Admin rolü ile erişim için [http://localhost:8080/auth/admin](http://localhost:8080/auth/admin)

Not: Uygulamayı çalıştırmadan önce gerekli bağımlılıkları yüklemek için Maven veya başka bir bağımlılık yönetici kullanmanız gerekebilir.
