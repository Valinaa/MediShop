import { MedicineStatusEnum } from '@/enums/medishopEnum'

export interface Medicine {
  id: number
  name: string
  summary: string
  price: number
  stock: number
  category: string
  status: MedicineStatusEnum
}

export interface MedicineDetail {
  id: number
  medicineId: number
  medicineSpecification: string
  description: string
  sideEffects: string
  ingredients: string
  imageUrl: string
  manufactureDate: Date
  validityDate: Date
}
