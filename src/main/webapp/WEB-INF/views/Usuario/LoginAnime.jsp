<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Anime Pink Landing Page</title>
    <style>
        body {
    margin: 0;
    font-family: 'Comic Sans MS', cursive, sans-serif;
    background-color: #ffe6f2;
    color: #333;
    text-align: center;
}

header {
    background-color: #ff99cc;
    padding: 20px;
}

.logo {
    font-size: 2em;
    color: white;
}

nav ul {
    list-style: none;
    padding: 0;
}

nav ul li {
    display: inline;
    margin: 0 15px;
}

nav ul li a {
    color: white;
    text-decoration: none;
    font-weight: bold;
}

.hero {
    background-color: #ffb3d9;
    padding: 100px 20px;
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://w0.peakpx.com/wallpaper/633/897/HD-wallpaper-pc-kawaii-anime-kawaii.jpg');
    background-size: cover;
    background-position: center;
}

.hero-content h1 {
    font-size: 3em;
    color: white;
}

.hero-content p {
    font-size: 1.5em;
    color: white;
    margin-bottom: 20px;
}

.cta-button {
    display: inline-block;
    padding: 15px 30px;
    background-color: #ff66b2;
    color: white;
    text-decoration: none;
    font-size: 1.2em;
    border-radius: 30px;
    transition: background-color 0.3s;
}

.cta-button:hover {
    background-color: #ff4d94;
}

section {
    padding: 60px 20px;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
}

h2 {
    font-size: 2.5em;
    color: #ff4da6;
    margin-bottom: 30px;
}

.feature-list {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
}

.feature-item {
    background-color: #fff0f5;
    padding: 20px;
    margin: 10px;
    border-radius: 15px;
    width: 300px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.feature-item h3 {
    font-size: 1.8em;
    color: #ff3385;
    margin-bottom: 10px;
}

footer {
    background-color: #ff99cc;
    padding: 30px 20px;
    color: white;
}

    </style>
</head>
<body>
    <header>
        <div class="logo">🌸 Sistema de gestión Minex 🌸</div>
        <nav>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#about">Registros</a></li>
                <li><a href="#features">Admin</a></li>
                <li><a href="#contact">Contacto</a></li>
            </ul>
        </nav>
    </header>

    <section id="home" class="hero">
        <div class="hero-content">
            <h1>Bienvenido a los registros de la empresa uwu</h1>
            <p>Si eres control y quieres registrar un despacho haz clic aquí:</p>
            <a href="#about" class="cta-button">👉👈</a>
        </div>
    </section>

    <section id="about">
        <div class="container">
            <h2>About Us</h2>
            <p>At Kawaii World, we celebrate the cute and colorful world of anime. Our community is passionate about sharing the joy of anime with everyone!</p>
        </div>
    </section>

    <section id="features">
        <div class="container">
            <h2>Features</h2>
            <div class="feature-list">
                <div class="feature-item">
                    <h3>Adorable Characters</h3>
                    <p>Explore a wide range of anime characters that will melt your heart.</p>
                </div>
                <div class="feature-item">
                    <h3>Colorful Themes</h3>
                    <p>Personalize your experience with a variety of vibrant themes.</p>
                </div>
                <div class="feature-item">
                    <h3>Anime Community</h3>
                    <p>Join a community of like-minded anime enthusiasts and share your love for anime.</p>
                </div>
            </div>
        </div>
    </section>

    <footer id="contact">
        <div class="container">
            <h2>Contact Us</h2>
            <p>Got a question or just want to say hello? Reach out to us!</p>
            <a href="mailto:contact@kawaiiworld.com" class="cta-button">Send an Email</a>
        </div>
    </footer>
</body>
</html>
