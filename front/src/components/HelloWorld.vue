<template>
  <div class="header-layout">
    <div class="login-box">
      <v-btn text> login</v-btn>
      <v-btn text> join</v-btn>
    </div>

    <div>
      <div class="">
        <div class="text-center">
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                Dropdown
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                  v-for="(store, index) in store"
                  router :to="store.route"
                  :key="index"

              >
                <v-list-item-title>{{ store.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                Dropdown
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                  v-for="(store, index) in store"
                  router :to="store.route"
                  :key="index"

              >
                <v-list-item-title>{{ store.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <v-divider class="divider-padding"></v-divider>
      </div>

    </div>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data: () => ({
   store : [
      { title: '호텔', route: '/hotel' },
      { title: '펜션' , route: '/hotel'},
      { title: '글램핑' , route: '/hotel'},
      { title: '리조트' },
      { title: '게스트하우스' }
    ]
  }),
  methods: {}
};
</script>

<style scoped>
.header-layout {
  display: flex;
  height: 20vh;
  flex-direction: column;
  justify-content: space-between;
}
.login-box {
  display: flex;
  justify-content: end;
}
.hotel-list-form {
  display: flex;
  align-items: center;
}
.select-box {
  padding: 0 2%;
}
.select-size {
  width: 50%;
}
.divider-padding {
  margin-top: 10px;
}
.a {

}
</style>
