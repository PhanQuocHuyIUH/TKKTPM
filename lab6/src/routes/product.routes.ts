import { Router } from "express";
import productController from "../controllers/product.controller";

const router = Router();

router.post("/", (req, res) => productController.create(req, res));
router.get("/", (req, res) => productController.getAll(req, res));
router.get("/:id", (req, res) => productController.getById(req, res));

export default router;
