<template>
  <div class="">
    <div class="appbar-desktop d-none d-lg-block border-bottom">
      <b-navbar variant="faded" type="light" toggleable="lg" class="py-0">

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-navbar-brand class="mb-0">
            <span style="font-size: 20px; font-weight: 700">
                Sneakers
            </span>
        </b-navbar-brand>

        <b-collapse id="nav-collapse" is-nav class="">
          <b-navbar-nav>
            <b-nav-item class="py-4 category" v-for="(category, index) in categories" :key="index">
              <div class="px-2">
                <span class="text-muted text">
                  {{ category }}
                </span>
              </div>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>

        <b-navbar-nav class="ml-auto d-flex flex-row align-items-center">
          <b-nav-item class="col-6">
            <b-dropdown size="xl"  variant="link" toggle-class="text-decoration-none" no-caret right>
              <template #button-content>
                <b-img src="/assets/icon-cart.svg" alt=""></b-img>
              </template>

              <!-- <b-dropdown-item href="#"> -->
              <div style="min-width: 350px">
                <div class="d-flex flex-column">
                  <div class="col-12 p-3 border-bottom">
                                <span style="font-weight: 700">
                                    Cart
                                </span>
                  </div>
                  <div class="col-12" style="max-height: 200px; overflow: auto">
                    <div v-if="cartItems && cartItems.length > 0">
                      <div v-for="(item, index) in cartItems" :key="index" class="d-flex flex-row">
                        <div>

                        </div>
                        <div class="d-flex flex-column">
                          <div>
                                                <span>
                                                    {{ item.title }}
                                                </span>
                          </div>
                          <div>
                                                <span class="text-muted">
                                                    {{ item.price }} X {{ item.quantity }}
                                                </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div v-else>
                      <div class="row justify-content-center align-items-center" style="min-height: 200px">
                        <div class="col-12 text-center">
                                            <span class="text-muted" style="font-weight: 700">
                                                Your cart is empty.
                                            </span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- </b-dropdown-item> -->
            </b-dropdown>
          </b-nav-item>
          <b-nav-item class="col-6">
                <span>
                <b-img src="./assets/image-avatar.png" class="avatar" rounded="circle" alt="Circle image" height="40px"></b-img>
                </span>
          </b-nav-item>
        </b-navbar-nav>

      </b-navbar>
    </div>

    <div class="appbar-mobile d-lg-none d-md-block">
      <b-navbar variant="faded" type="light" toggleable="lg">
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-navbar-brand tag="h1" class="mb-0 mx-3">
            <span style="font-size: 20px; font-weight: 700">
                Sneakers
            </span>
        </b-navbar-brand>

        <b-navbar-nav class="ml-auto d-flex flex-row justify-content-between align-items-center">
          <b-nav-item class="col-6">
            <b-dropdown size="lg"  variant="link" toggle-class="text-decoration-none" no-caret>
              <template #button-content>
                <b-img src="/assets/icon-cart.svg" alt=""></b-img>
              </template>

              <b-dropdown-item href="#">Action</b-dropdown-item>
            </b-dropdown>
          </b-nav-item>
          <b-nav-item class="col-6">
                <span>
                <b-img src="./assets/user.jpg" rounded="circle" alt="Circle image" height="35px"></b-img>
                </span>
          </b-nav-item>
        </b-navbar-nav>

        <b-collapse id="nav-collapse" is-nav class="">
          <b-navbar-nav>
            <b-nav-item class="">
              <div>
                <span class="text-muted">
                  Collections
                </span>
              </div>
            </b-nav-item>
            <b-nav-item>
              <div>
                <span class="text-muted">
                  Men
                </span>
              </div>
            </b-nav-item>
            <b-nav-item>
              <div>
                <span class="text-muted">
                  Women
                </span>
              </div>
            </b-nav-item>
            <b-nav-item>
              <div>
                <span class="text-muted">
                  About
                </span>
              </div>
            </b-nav-item>
            <b-nav-item>
              <div>
                <span class="text-muted">
                  Contact
                </span>
              </div>
            </b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "StoreDetail",
  props: ['store_name'],

  data() {
    return {
      mainCategory: 'Collections',
      categories: [
        'Collections',
        'Men',
        'Women',
        'About',
        'Contact',
      ],
      cartItems: [],
    }
  },

  computed: {
    hotel() {
      return this.$store.state.stores;

    }
  },

  mounted() {
    this.importStore()
    const items = JSON.parse(localStorage.getItem('myCart'));
    console.log('items', items);
    this.cartItems = items;
  },

  methods: {
    reserve() {
      this.loading = true
      setTimeout(() => (this.loading = false), 2000)
    },

    importStore() {
      axios({
        method: 'get',
        url: `/api/store/` + this.store_name,
      })
        .then((res) => {
          this.lists = res.data;
        })
    }
  },
}
</script>

<style scoped>
.avatar:hover {
  border: 2px solid hsl(26, 100%, 55%) !important;
}
.category {
  transition: 0.1s;
}
.category:hover {
  border-bottom: 3px solid hsl(26, 100%, 55%);
}
.category:hover .text {
  color: black !important;
}
</style>
