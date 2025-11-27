# -----------------------------
# 1️⃣ مرحلة البناء
# -----------------------------
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# نسخ ملفات المشروع
COPY pom.xml .
COPY src ./src

# بناء المشروع وإنتاج jar (نتجنب الاختبارات باش يكون أسرع)
RUN mvn clean package -DskipTests

# -----------------------------
# 2️⃣ مرحلة التشغيل
# -----------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# نسخ jar من مرحلة البناء
COPY --from=build /app/target/*.jar app.jar

# تعيين البورت
EXPOSE 8888

# تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
