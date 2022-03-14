<template>
  <div class="header-layout">
    <div class="login-box">
      <v-btn text> login</v-btn>
      <v-btn text> join</v-btn>
    </div>
    <div>
      <div class="">
        <v-row justify="space-around">
          <v-menu
              bottom
              origin="center center"
              transition="slide-y-transition"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="" dark v-bind="attrs" v-on="on">
                여행지
              </v-btn>
            </template>

            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu transition="slide-y-transition" bottom right>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  class="deep-orange"
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                여행계획
              </v-btn>
            </template>

            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu transition="slide-y-transition" bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  class="purple"
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                시설
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <v-menu
              bottom
              origin="center center"
              transition="slide-y-transition"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                가방관리
              </v-btn>
            </template>

            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu transition="slide-y-transition" bottom right>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  class="deep-orange"
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                현지 가이드
              </v-btn>
            </template>

            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu transition="slide-y-transition" bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  class="purple"
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                커뮤니티
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-menu transition="slide-y-transition" bottom>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  class="purple"
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                고객센터
              </v-btn>
            </template>
            <v-list>
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-title>{{
                    item.title
                  }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-row>
      </div>
      <v-divider class="divider-padding"></v-divider>
    </div>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data: () => ({
    items: [
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me' },
      { title: 'Click Me 2' }
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
  /* margin: 1%; */
}
</style>
