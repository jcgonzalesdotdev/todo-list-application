import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'

const app = createApp(App)

// Define the tooltip directive
app.directive('tooltip', {
  mounted(el, binding) {
    // Create the tooltip element
    const tooltip = document.createElement('div')
    tooltip.className = 'custom-tooltip'
    tooltip.textContent = binding.value
    tooltip.style.position = 'absolute'
    tooltip.style.backgroundColor = '#333'
    tooltip.style.color = '#fff'
    tooltip.style.padding = '5px'
    tooltip.style.borderRadius = '3px'
    tooltip.style.zIndex = '9999'
    tooltip.style.display = 'none' // Initially hide the tooltip

    // Append the tooltip to the body
    document.body.appendChild(tooltip)

    // Position the tooltip relative to the element
    el.addEventListener('mouseenter', function () {
      const rect = el.getBoundingClientRect()
      tooltip.style.top = rect.top + window.pageYOffset + 'px'
      tooltip.style.left = rect.left + 'px'
      tooltip.style.display = 'block'
    })

    el.addEventListener('mouseleave', function () {
      tooltip.style.display = 'none'
    })
  }
})

app.use(router)
app.mount('#app')
