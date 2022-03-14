<template>
  <div class="header-layout">
    <div class="login-box">
      <v-btn text> login</v-btn>
      <v-btn text> join</v-btn>
    </div>

    <div>
      <v-menu :rounded="rounded" offset-y>
        <template v-slot:activator="{ attrs, on }">
          <v-btn
              :color="colors[index]"
              class="blue"
              v-bind="attrs"
              v-on="on"
          >
            여행지
          </v-btn>
        </template>

        <v-list>
          <v-list-item
              v-for="item in items"
              :key="item"
              link
          >
            <v-list-item-title v-text="item"></v-list-item-title>
          </v-list-item>
        </v-list>

      </v-menu>
      <v-menu>여행계획</v-menu>
      <v-menu>시설</v-menu>
      <v-menu>가방관리</v-menu>
      <v-menu>현지 가이드</v-menu>
      <v-menu>커뮤니티</v-menu>
      <v-menu>고객센터</v-menu>
    </div>

  </div>

</template>
<script>

export default {
  name: 'HelloWorld',
  data: () => ({
    btn: [
      ['여행지'],
      ['여행계획'],
      ['시설'],
      ['가방관리'],
      ['현지 가이드'],
      ['커뮤니티'],
      ['고객센터'],

    ],
    colors: ['blue', 'blue', 'blue', 'blue', 'blue', 'blue', 'blue'],
    item: ['응가1', '응가2', '응가3', '응가4', '응가5'],
    items: ['이용안내', '가방예약', '요금', '후기', '배송조회'],

    // items: [
    //   {title: 'click'},
    //   {title: 'click'},
    //   {title: 'click'},
    //   {title: 'click'},
    //   {title: 'click'}
    // ],
    bag: [
      {title: '이용안내'},
      {title: '가방 예약'},
      {title: '요금'},
      {title: '후기'},
      {title: '배송조회'}
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
