
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue') },
      { path: 'PersonsList', component: () => import('pages/PersonsList.vue') },
      { name: 'AddNewPerson',path: 'AddNewPerson', component: () => import('pages/AddNewPerson.vue') }
    ]
  },
  {
    path: '/AddNewPerson',
    component: () => import('pages/AddNewPerson.vue')
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
