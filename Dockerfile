FROM eclipse-temurin:25-jdk

RUN apt-get update && apt-get install -y \
    xvfb \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .
RUN javac -d out $(find src -name "*.java")

ENTRYPOINT ["bash", "run.sh"]