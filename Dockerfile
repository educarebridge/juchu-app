# 1. Gradleを使ってビルドする環境（Java 21）
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .
# テストをスキップしてJARファイルを生成
RUN gradle bootJar -x test --no-daemon

# 2. 実行用環境（Java 21）
FROM eclipse-temurin:21-jre
WORKDIR /app
# ビルドされたJARファイルをコピー
COPY --from=build /app/build/libs/*.jar app.jar

# ポート設定と実行
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
