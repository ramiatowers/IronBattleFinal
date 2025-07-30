# ⚔️ Java RPG Battle Simulator

Este es un proyecto en Java que simula un juego de batalla por turnos entre personajes de dos clases: **Warriors** y **Wizards**. Los usuarios pueden crear personajes, visualizarlos, enfrentarlos en combate, o importarlos desde un archivo CSV.

---

## 🧠 Tecnologías utilizadas

- Java 17+
- Programación Orientada a Objetos (OOP)
- Clases abstractas e interfaces (`GameCharacter`, `Attacker`)
- Validación de atributos
- Manejo de ficheros (importación de personajes vía CSV)
- Uso de consola y `Scanner`

---

## 🕹️ Cómo usar

1. **Ejecuta `Main.java`**.
2. Se abrirá un menú con las siguientes opciones:

```
1. Create character
2. Show characters
3. Start battle
4. Exit
5. Random battle
6. Import characters from CSV
```

---

## 👤 Clases y lógica del juego

### `GameCharacter` (abstract class)
- Clase base con propiedades comunes (`name`, `hp`, `defense`, etc.).
- Método `receiveDamage()` con lógica de defensa aleatoria.
- Es extendida por `Warrior` y `Wizard`.

### `Attacker` (interface)
- Define el método `attack(GameCharacter target)` que deben implementar todas las clases combatientes.

### `Warrior` y `Wizard`
- Clases concretas con atributos y ataques específicos:
  - **Warrior:** fuerza + stamina, ataques pesados y débiles.
  - **Wizard:** inteligencia + mana, lanza bolas de fuego o ataques débiles mágicos.

---

## 📦 Importación desde CSV

Puedes cargar personajes desde un archivo `.csv` con el siguiente formato (incluye encabezado):

```csv
name,type,hp,resource,power
Thorne,warrior,180,30,7
Nyx,wizard,80,40,45
```

- `type`: `"warrior"` o `"wizard"`
- `resource`: `stamina` para Warriors, `mana` para Wizards
- `power`: `strength` (Warrior) o `intelligence` (Wizard)

---

## 🧪 Ejemplo de batalla

Los personajes luchan por turnos hasta que uno o ambos mueren. El sistema de combate incluye:

- Ataques aleatorios (fuertes o débiles)
- Consumo y regeneración de recursos
- Sistema de defensa con reducción de daño

---

## ✅ Funcionalidades implementadas

- [x] Crear personajes manualmente
- [x] Mostrar lista de personajes
- [x] Simular batalla entre dos seleccionados
- [x] Generar batalla aleatoria entre personajes generados automáticamente
- [x] Importar personajes desde CSV
- [x] Validaciones de atributos al crear personajes

---

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   ├── Main.java
│   │   ├── org/example/
│   │   │   ├── GameCharacter.java
│   │   │   ├── Attacker.java
│   │   │   ├── Warrior.java
│   │   │   └── Wizard.java
```

---

## 🚀 Créditos

Proyecto desarrollado por [Tu Nombre Aquí] como parte del módulo de Programación Orientada a Objetos en Java.

---
