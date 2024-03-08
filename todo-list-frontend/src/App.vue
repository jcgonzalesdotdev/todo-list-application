<template>
  <div id="app">
    <HeaderSection />
    <Task />
  </div>
</template>

<script>
import Task from './components/TaskInit.vue'
import HeaderSection from './components/landing-page/header-section.vue'
// import MainSection from './components/landing-page/main-section.vue'
// import FooterSection from './components/landing-page/footer-section.vue'

export default {
  name: 'App',
  components: {
    Task,
    HeaderSection
    // HeaderSection,
    // MainSection,
    // FooterSection
  }
}
</script>

<style scoped>

header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }

  #app {
    background-image: url('https://th.bing.com/th/id/OIP.fmLeWCb8UTJHF2DMEyY8lAHaEL?rs=1&pid=ImgDetMain');
    background-size: cover;
    background-position: center;
    min-height: 100vh;
  }
}
</style>
