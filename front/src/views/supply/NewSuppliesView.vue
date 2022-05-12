<template>
  <v-container
  fluid
  >
    트리플의 카테고리형식
    <v-card
      class="mx-auto"
      width="300"
    >
      <v-list>
        <v-list-item>
          <v-list-item-icon>
            <v-icon>mdi-home</v-icon>
          </v-list-item-icon>

          <v-list-item-title>Home</v-list-item-title>
        </v-list-item>

        <v-list-group
          :value="true"
          prepend-icon="mdi-account-circle"
        >
          <template v-slot:activator>
            <v-list-item-title>Users</v-list-item-title>
          </template>

          <v-list-group
            :value="true"
            no-action
            sub-group
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>Admin</v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="([title, icon], i) in admins"
              :key="i"
              link
            >
              <v-list-item-title v-text="title"></v-list-item-title>

              <v-list-item-icon>
                <v-icon v-text="icon"></v-icon>
              </v-list-item-icon>
            </v-list-item>
          </v-list-group>

          <v-list-group
            no-action
            sub-group
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>Actions</v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="([title, icon], i) in cruds"
              :key="i"
              link
            >
              <v-list-item-title v-text="title"></v-list-item-title>

              <v-list-item-icon>
                <v-icon v-text="icon"></v-icon>
              </v-list-item-icon>
            </v-list-item>
          </v-list-group>
        </v-list-group>
      </v-list>
    </v-card>
    <v-row
    justify="center"
    >

      <v-col  cols="3" class="">
        <h2 class="text-h4 success--text pl-4">
          할 일:&nbsp;
          <v-fade-transition leave-absolute>
        <span :key="`tasks-${tasks.length}`">
          {{ tasks.length }}
        </span>
          </v-fade-transition>
        </h2>

      </v-col>
      <v-col
      cols="3"
      >
        <v-row

          align="center"

        >
          <v-divider vertical></v-divider>

          <v-col>
            남은 할일 수: {{ remainingTasks }}
          </v-col>
          <v-divider vertical></v-divider>

          <v-col>
            완료된 수: {{ completedTasks }}

          </v-col>

          <v-spacer></v-spacer>

          <v-progress-circular
            :value="progress"
            class="mr-2"
          ></v-progress-circular>

        </v-row>

      </v-col>

    </v-row>
    <v-row>
      <v-divider class="mt-4"></v-divider>

    </v-row>
    <v-row
      justify="center"
    >
      <v-col
        cols="3"
      >

        <v-card v-if="tasks.length > 0">
          <v-slide-y-transition
            class="py-0"
            group
            tag="v-list"
          >
            <template v-for="(task, i) in tasks">
              <v-divider
                v-if="i !== 0"
                :key="`${i}-divider`"
              ></v-divider>

              <v-list-item :key="`${i}-${task.text}`">
                <v-list-item-action>
                  <v-checkbox
                    v-model="task.done"
                    :color="task.done && 'grey' || 'primary'"
                  >
                    <template v-slot:label>
                      <div
                        :class="task.done && 'grey--text' || 'primary--text'"
                        class="ml-4"
                        v-text="task.text"
                      ></div>
                    </template>
                  </v-checkbox>
                </v-list-item-action>

                <v-spacer></v-spacer>

                <v-scroll-x-transition>
                  <v-icon
                    v-if="task.done"
                    color="success"
                  >
                    mdi-check
                  </v-icon>
                </v-scroll-x-transition>
              </v-list-item>
            </template>
          </v-slide-y-transition>
        </v-card>
      </v-col>
      <v-col
        cols="3"
      >
        <v-text-field
          v-model="newTask"
          label="할일을 적어주세요"
          solo
          @keydown.enter="create"
          height="200px"
        >
          <template v-slot:append>
            <v-fade-transition>
              <v-icon
                v-if="newTask"
                @click="create"
              >
                mdi-plus-circle
              </v-icon>
            </v-fade-transition>
          </template>
        </v-text-field>
        <v-card>
          <h2>임포트Set1</h2>
        </v-card>
        <v-card>
          <h2>임포트Set2</h2>
        </v-card>
        <v-card>
          <h2>임포트Set3</h2>
        </v-card>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
export default {
  name: "NewSuppliesView",
  data: () => ({
    admins: [
      ['Management', 'mdi-account-multiple-outline'],
      ['Settings', 'mdi-cog-outline'],
    ],
    cruds: [
      ['Create', 'mdi-plus-outline'],
      ['Read', 'mdi-file-outline'],
      ['Update', 'mdi-update'],
      ['Delete', 'mdi-delete'],
    ],
    tasks: [
      {
        done: false,
        text: 'Foobar',
      },
      {
        done: false,
        text: 'Fizzbuzz',
      },
    ],
    newTask: null,
  }),

  computed: {
    completedTasks () {
      return this.tasks.filter(task => task.done).length
    },
    progress () {
      return this.completedTasks / this.tasks.length * 100
    },
    remainingTasks () {
      return this.tasks.length - this.completedTasks
    },
  },

  methods: {
    create () {
      this.tasks.push({
        done: false,
        text: this.newTask,
      })

      this.newTask = null
    },
  },
}
</script>

<style>

</style>
