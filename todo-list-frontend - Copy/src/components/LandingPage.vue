<template>
  <div class="pad">
    <h1>TODO LIST</h1>
    <span class="peel">
      <!-- <span class="peel-back"></span> -->
      <RouterLink to="/task">
        <button class="peel-back" style="border: 0cap"></button>
      </RouterLink>
    </span>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>

<style scoped>
body {
  background-color: #d4ffff;
  text-align: center;
}

.pad {
  position: relative;
  z-index: 2;
  margin: 0 auto;
  /* margin-top: 50px; */
  width: 400px;
  height: 400px;
  background-color: #64b8d1;
  box-shadow: 0 -1px 14px 2px #333; /* Adjusted box-shadow values */
  border-bottom: 6px solid #0f3960; /* Adjusted border thickness */
  border-radius: 13px;
  display: flex; /* Add display flex */
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}

.pad h1 {
  font-weight: normal;
  color: #0f3960;
  font-weight: normal;
  position: absolute;
  z-index: 20;
  top: 50%; /* Center vertically within the .pad */
  left: 50%; /* Center horizontally within the .pad */
  transform: translate(-50%, -50%); /* Centering trick */
}

.peel {
  position: absolute;
  z-index: 0;
  bottom: 0;
  right: 0;
  height: 120px; /* Doubled height */
  width: 120px; /* Doubled width */
  background-color: #4d92a6;
  transition:
    width 500ms,
    height 500ms,
    transform 500ms;
}

.peel:hover {
  height: 370px; /* Doubled height */
  width: 370px; /* Doubled width */
  transition:
    width 500ms,
    height 500ms,
    transform 500ms;

  &::before {
    height: 200px; /* Doubled height */
    width: 400px; /* Doubled width */
    transition:
      width 500ms,
      height 500ms,
      transform 500ms;
  }

  &::after {
    height: 370px; /* Doubled height */
    width: 200px; /* Doubled width */
    right: 200px; /* Adjusted right positioning */
    transition:
      width 500ms,
      height 500ms,
      right 500ms,
      transform 500ms;
  }

  .peel-back {
    bottom: 34px; /* Adjusted bottom positioning */
    right: 130px; /* Adjusted right positioning */
    width: 160px; /* 160 Doubled width */
    height: 290px; /* 310 Doubled height */
    transition:
      width 500ms,
      height 500ms,
      right 500ms,
      bottom 500ms,
      transform 500ms;
    border: #d4ffff;
  }
}

.peel::before {
  position: absolute;
  z-index: 5;
  top: 0;
  right: 0;
  content: '';
  height: 62px; /* Doubled height */
  width: 122px; /* Doubled width */
  background-color: #64b8d1;
  border-bottom: 2px #0f3960 solid; /* Adjusted border thickness */
  border-radius: 0 0 50% 0;
  transition:
    width 500ms,
    height 500ms,
    transform 500ms;
  background: linear-gradient(76% 85%, #64b8d1, #0f3960);
}

.peel::after {
  position: absolute;
  z-index: 6;
  bottom: 0px;
  right: 60px; /* Adjusted right positioning */
  content: '';
  height: 100px; /* Doubled height */
  width: 62px; /* Doubled width */
  background-color: #64b8d1;
  border-radius: 0 0 50% 0;
  transition:
    width 500ms,
    height 500ms,
    right 500ms,
    transform 500ms;
  background: linear-gradient(56% 94%, #64b8d1, #0f3960);
}

.peel-back {
  position: absolute;
  z-index: 2;
  bottom: 10px; /* Adjusted bottom positioning */
  right: 38px; /* Adjusted right positioning */
  width: 40px; /* Doubled width */
  height: 92px; /* Doubled height */
  background-color: #3f6180;
  transform: rotate(45deg);
  transition:
    width 500ms,
    height 500ms,
    right 500ms,
    bottom 500ms,
    transform 500ms;
  box-shadow: 0 0 10px 4px #3f6180; /* Adjusted box-shadow values */
}
</style>
