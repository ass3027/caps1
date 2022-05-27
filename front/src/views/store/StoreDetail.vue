<template>
  <div class="">
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    >
    <div class="container my-0 my-md-4">
      <div class="row align-items-center justify-content-between">
        <div class="col-12 col-md-5 p-0 p-md-2">
          <div class="d-flex flex-column">
            <div class="col-12 px-md-2 d-none d-md-block">
              <div
                class=""
                style="cursor: pointer"
              >
                <v-img
                  v-if=" temp===1"
                  :src="'/api/photo/' + products[index].pic_name"
                  alt=""
                  style="width: 100%"
                  class="image"
                  @click="showMainImage()"
                />
              </div>
            </div>

            <!--            <div class="d-block d-md-none col-12 px-0">-->
            <!--              <b-carousel v-model="slide" controls :interval="0">-->
            <!--                <b-carousel-slide v-for="(product, index) in this.products" :key="index" :img-src="image">-->
            <!--                </b-carousel-slide>-->
            <!--              </b-carousel>-->
            <!--            </div>-->

            <div class="col-12 d-none d-md-block my-4">
              <div class="row">
                <div
                  v-for="(product, index) in products"
                  :key="index"
                  class="col-3"
                >
                  <div
                    class="thumbnail"
                    @click="changeMainImage(index)"
                  >
                    <v-img
                      :src="'/api/photo/' + product.pic_name"
                      style="width:100%"
                      alt=""
                      class="image"
                      :class="mainImage === product.pic_name ? 'activess' : ''"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12 col-md-6 text-left my-3">
          <div class="container">
            <div>
              <span style="font-size: 13px; letter-spacing: 1px; color: hsl(26, 100%, 55%); font-weight: 700">
                Packless Travel
              </span>
            </div>

            <div class="mt-2 mb-4">
              <span style="font-size: 40px; font-weight: 900; color: black; line-height: 2.8rem">
                {{ products[0].store_id }}
              </span>
            </div>

            <div class="my-3">
              <span class="text-muted">
                개인 바베큐 됩니다.
              </span>
            </div>

            <div class="row">
              <div class="col-8 col-md-12 d-flex flex-row align-items-center">
                <div>
                  <span style="font-size: 24px; font-weight: 900">
                    ${{ parseFloat(price).toFixed(2) }}
                  </span>
                </div>
                <div class="">
                  <span
                    class="mx-3 discount px-2 py-1 rounded"
                    style="font-weight: 900; font-size: 14px; color: hsl(26, 100%, 55%)"
                  >
                    50%
                  </span>
                </div>
              </div>

              <div class="col-4 col-md-12 my-1">
                <span
                  class=""
                  style="font-weight: 700; color: rgb(183, 183, 183); text-decoration: line-through;"
                >
                  $250.00
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <StoreReviewView :store_name="store_name" />
  </div>
</template>

<script>
import StoreReviewView from "@/views/StoreReviewView";
import axios from "axios";

export default {
  name: 'ProductPage',
  components: {
    StoreReviewView
  },
  props: ['store_id'],
  data() {
    return {
      title: '5성급 편안호텔',
      price: "125.000",
      count: 1,
      images: [
        // require('@/image/product1.png'),
        // require('@/image/product3.png'),
        // require('@/image/product2.png'),
        // require('@/image/product4.png'),
      ],
      products: [],

      showImageModal: false,
      slide: 0,
      temp:0,
      index:0,

      store_name:'',
    }
  },
  computed: {
    mainImage: function(){
      return this.products[this.index].pic_name
    } ,

    cartItemsCount() {
      return this.cartItems.length;
    }
  },

  created() {
    axios({
      method: 'GET',
      url: '/api/product',
      params: {'pl_id': this.store_id}
    })
      .then((res) => {
        this.products = res.data
        this.temp++
        console.log(this.products)
      })
      .catch((err) => {
        console.log(err)
      })

    axios({
      method: 'GET',
      url: '/api/findPlName',
      params: {'store_id': this.store_id}
    })
      .then((res) => {
        this.store_name = res.data
      })

    const items = JSON.parse(localStorage.getItem('myCart'));
    // console.log('items', items);
    this.cartItems = items;
  },
  methods: {
    showMainImage() {
      this.showImageModal = true;
    },
    changeMainImage(index) {
      this.index = index;
    },
    calcPrice(item) {
      return parseFloat(parseFloat(item.price).toFixed(2) * item.quantity).toFixed(2);
    },
  }
}
</script>
<style scoped>
.image {
  border-radius: 10px;
}

@media (max-width: 768px) {
  .image {
    border-radius: 0px !important;
  }
}

.discount {
  /* border-radius: 10px; */
  background-color: hsl(25, 100%, 94%);
}

.bg-quantity {
  background-color: #f1f1f1;
  border-radius: 10px;
  font-size: 16px;
  padding: 14px;
}

.bg-add-cart {
  background-color: hsl(26, 100%, 55%);
  color: white !important;
  border-radius: 10px;
  font-size: 15px;
  padding: 14px;
  transition: 0.3s;
  cursor: pointer;
  box-shadow: 0px 23px 19px 10px hsl(25, 100%, 94%);
}

.bg-add-cart:hover {
  background-color: hsl(24, 91%, 86%);
}

.plus-minus {
  color: hsl(26, 100%, 55%);
  cursor: pointer;
}

.thumbnail img {
  cursor: pointer;
  opacity: 1;
  transition: .3s;
}

.thumbnail:hover img {
  /* background-color: #d2d2d2 !important;  */
  opacity: 0.3 !important;
}

.activess {
  opacity: 0.4 !important;
  border: 3px solid hsl(26, 100%, 55%) !important;
}

i {
  transition: 0.3s;
}

i:hover {
  color: hsl(24, 91%, 86%);
}

/* navbar */
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

.checkout-btn {
  transition: 0.3s;
}

.checkout-btn:hover {
  background-color: hsl(24, 91%, 86%) !important;
}

.cart-count {
  position: absolute;
  top: 3px;
  left: 22px;
  padding: 0px 7px;
  font-size: 9px;
  background-color: hsl(26, 100%, 55%);
  color: white;
  border-radius: 5px;
}
</style>
<style>
.dropdown-menu.dropdown-menu-right.show {
  padding: 0px !important;
  border: 0px !important;
}

.appbar-mobile .dropdown-menu.show {
  position: absolute;
  left: -220px;
  border: none;
  padding: 0px;
  border-radius: 15px;
}

.modal-content {
  background-color: transparent !important;
  border: none !important;
}

.modal-header {
  border: none !important;
}

.modal-header .close {
  opacity: 1 !important;
}

.carousel-control-prev {
  opacity: 1 !important;
  align-items: center !important;
  position: absolute;
  left: 0px !important;
}

.carousel-control-next {
  opacity: 1 !important;
  align-items: center !important;
  position: absolute;
  right: 0px !important;
}

.carousel-control-prev-icon {
  width: 30px !important;
  height: 30px !important;
}

.carousel-control-next-icon {
  width: 30px !important;
  height: 30px !important;
}

.navbar-toggler.collapsed {
  border: none;
  padding: 0px;
}

@media (max-width: 768px) {
  .carousel-item .img-fluid {
    border-radius: 0px !important;
  }
}

.carousel-item .img-fluid {
  border-radius: 30px;
}
</style>
