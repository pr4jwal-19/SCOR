/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/main/resources/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        'custom-dark': '#203369',
        'custom-gold': '#D6AF3C',
      }
    },
  },
  plugins: [],
  darkMode: "selector",
}

