# obraz Node jako bazowy
FROM node:lts AS build

# ustawienie katalogu roboczego
WORKDIR /app

# skopiowanie plików package.json oraz package-lock.json
COPY package.json ./
COPY package-lock.json ./

# zainstalowanie zależności
RUN npm install

# skopiownaie reszty plików aplikacji
COPY . .

# budowanie aplikację
RUN npm run build

# uycie obrazu Nginx do serwowania
FROM nginx:alpine

# usunięcie domyślnej konfiguracji Nginx
RUN rm -rf /usr/share/nginx/html/

# skopiowanie zbudowanych plików aplikacji z etapu budowania
COPY --from=build /app/build /usr/share/nginx/html

# skopiowanie własnego plik konfiguracyjnego Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# uruchomienie serwera Nginx
CMD ["nginx", "-g", "daemon off;"]
