<template>
  <v-app class="header-layout">
    <div class="login-box">
      <v-btn text>
        login
      </v-btn>
      <v-btn text>
        join
      </v-btn>
    </div>
    <div>
      <div class="menu-Bar">
        <div class="text-center">
          <!--여행지-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                여행지
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(travel, index) in travel"
                :key="index"
                router
                :to="travel.route"
              >
                <v-list-item-title>{{ travel.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--여행계획-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                여행계획
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(plan, index) in plan"
                :key="index"
                router
                :to="plan.route"
              >
                <v-list-item-title>{{ plan.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--시설-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                시설
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(facility, index) in facility"
                :key="index"
                router
                :to="facility.route"
              >
                <v-list-item-title>{{ facility.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--가방관리-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
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
              <v-list-item
                v-for="(BagControl, index) in BagControl"
                :key="index"
                router
                :to="BagControl.route"
              >
                <v-list-item-title>{{ BagControl.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--현지인 가이드-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                현지인 가이드
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                v-for="(Guide, index) in Guide"
                :key="index"
                router
                :to="Guide.route"
              >
                <v-list-item-title>{{ Guide.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--커뮤니티-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                커뮤니티
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                v-for="(Community, index) in Community"
                :key="index"
                router
                :to="Community.route"
              >
                <v-list-item-title>{{ Community.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <!--고객센터-->
          <v-menu offset-y>
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                고객센터
              </v-btn>
            </template>

            <v-list>
              <v-list-item
                v-for="(Customer, index) in Customer"
                :key="index"
                router
                :to="Customer.route"
              >
                <v-list-item-title>{{ Customer.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </div>
      <v-divider class="divider-padding" />
    </div>
  </v-app>
</template>

<script>

export default {

  name: 'HelloWorld',
  data: () => ({
    travel: [
      {title: 'travel1', route: '/travel'},
      {title: 'travel2', route: '/travel'},
      {title: 'travel3', route: '/travel'},
      {title: 'travel4', route: '/travel'},
      {title: 'travel5', route: '/travel'}
    ],
    plan: [
      {title: '일정', route: '/calender'},
      {title: '준비물', route: '/supplies'},
      {title: 'plan3', route: '/plan'},
      {title: 'plan4', route: '/plan'},
      {title: 'plan5', route: '/plan'}
    ],
    facility: [
      {title: '호텔', route: '/facility'},
      {title: '펜션', route: '/facility'},
      {title: '글램핑', route: '/facility'},
      {title: '리조트', route: 'facility'},
      {title: '게스트하우스', route: 'facility'}
    ],
    BagControl: [
      {title: '가방예약', route: '/OrderView'},
      {title: '이용안내', route: '/UsageGuideView'},
      {title: '요금', route: '/FareView'},
      {title: '후기', route: '/ReviewView'},
      {title: '배송조회', route: '/bag'}
    ],
    Guide: [
      {title: 'Guide1', route: '/guide'},
      {title: 'Guide2', route: '/Guide'},
      {title: 'Guide3', route: '/Guide'},
      {title: 'Guide4', route: '/Guide'},
      {title: 'Guide5', route: '/Guide'}
    ],
    Community: [
      {title: 'Community1', route: '/Community'},
      {title: 'Community2', route: '/Community'},
      {title: 'Community3', route: '/Community'},
      {title: 'Community4', route: '/Community'},
      {title: 'Community5', route: '/Community'}
    ],
    Customer: [
      {title: 'Customer1 ', route: '/Customer'},
      {title: 'Customer2', route: '/Customer'},
      {title: 'Customer3', route: '/Customer'},
      {title: 'Customer4', route: '/Customer'},
      {title: 'Customer5', route: '/Customer'}
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
  justify-content: flex-end;
}

.menu-Bar {
  display: flex;
  justify-content: center;
}

.text-center {
  margin-top: 20px;
  display: flex;
  justify-content: center;
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
</style>
