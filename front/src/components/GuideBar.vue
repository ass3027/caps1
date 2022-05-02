<template>
  <div class="icon-bar">
    <router-link to="/">
      <i class="fa fa-home" />
    </router-link>
    <router-link to="/about">
      <i class="fas fa-question" />
    </router-link>
  </div>
</template>

<script>
export default {
  name:'GuideBar',

}

</script>

<style>
.icon-bar {
  height: 100%;
  width: 90px;
  background-color: #555;
  position: absolute; /* Fixed Sidebar (stay in place on scroll) */
  /*z-index: 1;*/ /* Stay on top */
  top: 103px; /* Stay at the top */
  left: 0;
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 0;
}

.icon-bar a {
  display: block;
  text-align: center;
  padding: 16px;
  transition: all 0.3s ease;
  color: white;
  font-size: 36px;

}

.icon-bar a:hover {
  background-color: #858585;
}

.active {
  background-color: #5eaf13;
}



</style>
